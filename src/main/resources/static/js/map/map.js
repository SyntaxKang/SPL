var map= document.getElementById("map_div") , startMarker,endMarker, markerLayer,routeLayer ;
// 페이지가 로딩이 된 후 호출하는 함수입니다.


function initTmap(){
    // map 생성
    // Tmap.map을 이용하여, 지도가 들어갈 div, 넓이, 높이를 설정합니다.
    map = new Tmap.Map({div:'map_div', // map을 표시해줄 div
        width:'100%',  // map의 width 설정
        height:'400px' // map의 height 설정
    });


    markerLayer = new Tmap.Layer.Markers();
    map.addLayer(markerLayer); //map에 Layer를 등록

    // HTML5의 geolocation으로 사용할 수 있는지 확인합니다
    if(navigator.geolocation){
        // GeoLocation을 이용해서 접속 위치를 얻어옵니다
        navigator.geolocation.getCurrentPosition(function(position){
            // 마커가 표시될 위치를 geolocation으로 얻어온 좌표로 생성합니다
            var lat = position.coords.latitude;
            var lon = position.coords.longitude;
            var PR_3857 = new Tmap.Projection("EPSG:3857");  // Google Mercator 좌표계인 EPSG:3857
            var PR_4326 = new Tmap.Projection("EPSG:4326");  // WGS84 GEO 좌표계인 EPSG:4326
            var lonlat = new Tmap.LonLat(lon, lat).transform(PR_4326, PR_3857);
            map.setCenter(lonlat, 16);

            //마커 생성
            var size = new Tmap.Size(24, 38);
            var offset = new Tmap.Pixel(-(size.w / 2), -(size.h));
            var icon = new Tmap.Icon('http://tmapapis.sktelecom.com/upload/tmap/marker/pin_b_m_s.png',size, offset);


            startMarker = new Tmap.Marker(lonlat, icon);
            markerLayer.addMarker(startMarker);
            endMarker = new Tmap.Marker(lonlat,icon);
            markerLayer.addMarker(endMarker);
            //마커를 레이어에 등록.
            map.events.register("click", map, mapClick);//map 클릭 이벤트를 등록합니다.

        });

    }

}






function mapClick(e){

    navigator.geolocation.getCurrentPosition(function(position){
        // 마커가 표시될 위치를 geolocation으로 얻어온 좌표로 생성합니다

        markerLayer.removeMarker(endMarker); // 기존 마커 삭제

        //시작점의 좌표(현재위치)
        var startLat =(position.coords.latitude).toString();
        var startLon = (position.coords.longitude).toString();


        //목적지의 좌표
        var finishPoinLonLat = map.getLonLatFromViewPortPx(e.xy).transform("EPSG:3857", "EPSG:4326");//클릭 부분의 ViewPortPx를 LonLat 좌표로 변환합니다.
        var endLon = (finishPoinLonLat.lon).toString();
        var endLat = (finishPoinLonLat.lat).toString();

        //목적지 마커 생성
        var size = new Tmap.Size(24, 38);//아이콘 사이즈 설정
        var offset = new Tmap.Pixel(-(size.w/2), -(size.h));//아이콘 중심점 설정
        var icon = new Tmap.Icon('http://tmapapis.sktelecom.com/upload/tmap/marker/pin_b_m_a.png',size, offset);//마커 아이콘 설정
        endMarker = new Tmap.Marker(finishPoinLonLat.transform("EPSG:4326", "EPSG:3857"), icon);//마커 생성
        markerLayer.addMarker(endMarker);//마커 레이어에 마커 추가.
        map.zoomOut(finishPoinLonLat);

        var headers = {};
        headers["appKey"]="2bd4f6d9-de5d-429a-8062-c8a415063781";
        $.ajax({
            method:"POST",
            headers : headers,
            url:"https://apis.openapi.sk.com/tmap/routes/pedestrian?version=1&format=xml",//보행자 경로안내 api 요청 url입니다.
            async:false,
            data:{
                //출발지 위경도 좌표입니다.
                startX : startLon,
                startY : startLat,
                //목적지 위경도 좌표입니다.
                endX : endLon,
                endY : endLat,
                //출발지, 경유지, 목적지 좌표계 유형을 지정합니다.
                reqCoordType : "WGS84GEO",
                resCoordType : "EPSG3857",
                //각도입니다.
                //출발지 명칭입니다.
                startName : "출발지",
                //목적지 명칭입니다.
                endName : "도착지"
            },
            //데이터 로드가 성공적으로 완료되었을 때 발생하는 함수입니다.
            success:function(response){
                prtcl = response;

                // 결과 출력
                var prtclString = new XMLSerializer().serializeToString(prtcl);//xml to String
                xmlDoc = $.parseXML( prtclString ),
                    $xml = $( xmlDoc ),
                    $intRate = $xml.find("Document");
                var tDistance = "총 거리 : "+($intRate[0].getElementsByTagName("tmap:totalDistance")[0].childNodes[0].nodeValue/1000).toFixed(1)+"km,";
                var tTime = " 총 시간 : "+($intRate[0].getElementsByTagName("tmap:totalTime")[0].childNodes[0].nodeValue/60).toFixed(0)+"분";
                $("#result").text(tDistance+tTime);
                $("#range").text(($intRate[0].getElementsByTagName("tmap:totalDistance")[0].childNodes[0].nodeValue/1000).toFixed(1));



                prtcl=new Tmap.Format.KML({extractStyles:true, extractAttributes:true}).read(prtcl);//데이터(prtcl)를 읽고, 벡터 도형(feature) 목록을 리턴합니다.
                routeLayer = new Tmap.Layer.Vector("route");// 백터 레이어 생성
                //표준 데이터 포맷인 KML을 Read/Write 하는 클래스 입니다.

                routeLayer.events.register("beforefeatureadded", routeLayer, onBeforeFeatureAdded); //벡터 도형(Feature)이 추가되기 직전에 이벤트가 발생합니다.
                function onBeforeFeatureAdded(e) {
                    var style = {};
                    switch (e.feature.attributes.styleUrl) {
                        case "#pointStyle":
                            style.externalGraphic = "http://topopen.tmap.co.kr/imgs/point.png"; //렌더링 포인트에 사용될 외부 이미지 파일의 url입니다.
                            style.graphicHeight = 16;//외부 이미지 파일의 크기 설정을 위한 픽셀 높이입니다.
                            style.graphicOpacity = 1;//외부 이미지 파일의 투명도 (0-1)입니다.
                            style.graphicWidth = 16;//외부 이미지 파일의 크기 설정을 위한 픽셀 폭입니다.
                            break;
                        default:
                            style.strokeColor = "#ff0000";//stroke에 적용될 16진수 color
                            style.strokeOpacity = "1";//stroke의 투명도(0~1)
                            style.strokeWidth = "5";//stroke의 넓이(pixel 단위)
                    };
                    e.feature.style = style;

                }

                routeLayer.addFeatures(prtcl);//레이어에 도형을 등록합니다.
                map.addLayer(routeLayer);//맵에 레이어 추가

                map.zoomToExtent(routeLayer.getDataExtent());//map의 zoom을 routeLayer의 영역에 맞게 변경합니다.

            },
            //요청 실패시 콘솔창에서 에러 내용을 확인할 수 있습니다.
            error:function(request,status,error){
                console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
            }
        });
    });
    console.log("리셋전"+routeLayer);

    map.events.unregister("click", map, mapClick);// onclick 이벤트해제(클릭이벤트 중복 방지)

}


function resetClick() {
    map.destroy();
    initTmap();
    $("#result").text(" ");

}

function saveClick() {
    var url = '/walk/insert';
    var type = 'POST';
    var data = {
        "range": $("#range").val(),
        "goal" : $("#goal").val(),
        "date" : $("#date").val(),
        "userIdx":$("#userIdx").val(),
        "groupIdx":$("#groupIdx").val()
    };
    $.ajax({
        url: url,
        type: type,
        data: data,
        success: function (data) {
            swal("저장했습니다!", "버튼을 클릭해주세요!", "success");
        },
        complete: function (data) {
            location.reload();
        }
    });

}



window.onload= function () {
    initTmap();
}