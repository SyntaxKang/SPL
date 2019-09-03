//볼링점수 계산
$(document).ready(function () {
    var total = 0;   //총점수
    var spare = 0;   //스페어 두번째투구에 첫투구와합이10일때
    var strike = 0;  // 스트라이크 첫투구에 10개쓰러트렸을때
    var perfectgame =0;
    //1frame
    //첫 투구
    $("#ball0_1").keyup(function () {
             //첫투구가 10일때
            if ($("#ball0_1").val() == 10) {
                $("#out0").text("X");
                total = Number($("#ball0_1").val());
                strike += 1;
                //console.log("1frame스트라이크:"+strike);
                $("#strike").val(strike);
                var charLimit = $(this).attr("maxlength");
                if (this.value.length >= charLimit) {
                    $("#ball1_1").focus();
                    return false;
                }
            }
            //첫투구가 스트라이크가 아닐때
            if ($("#ball0_1").val() < 10) {
                if (this.value.length >= 1) {
                    $("#ball0_2").focus();
                    return false;
                }
            }
    });
    //두번째 투구
    $("#ball0_2").keyup(function () {
        //첫투구와두번째투구합
        sum = Number($("#ball0_1").val()) + Number($("#ball0_2").val());
        //스페어
        if(sum==10){
            $("#out0").text("/");
            spare += 1;
            $("#spare").val(spare);
            total=total+sum;
            if (this.value.length >= 1) {
                $("#ball1_1").focus();
                return false;
            }
        }
        if(sum<10 && sum>=0){
            total=total+sum;
            $("#out0").text(total);
            if (this.value.length >= 1) {
                $("#ball1_1").focus();
                return false;
            }
        }
        else{
            //10이 넘을경우
            $("#ball0_1").val('');    //초기화
            $("#ball0_2").val('');   //초기화
            alert("합이10이넘었어요");
            $("#ball0_1").focus();
        }
    });

    //2frame
    $("#ball1_1").keyup(function () {
        if($("#ball1_1").val()==10) {
            $("#out1").text("X");
            sum = Number($("#ball1_1").val());
            total = total +sum;
            strike += 1;
            $("#strike").val(strike);
            console.log("2frame스트라이크:"+strike);
            $("#strike").val(strike);
            var charLimit = $(this).attr("maxlength");
            if (this.value.length >= charLimit) {
                $("#ball2_1").focus();
                return false;
            }
        }
        if($("#ball1_1").val()<10) {
            if (this.value.length >= 1) {
                $("#ball1_2").focus();
                return false;
            }
        }
    });
    $("#ball1_2").keyup(function () {
        sum = Number($("#ball1_1").val()) + Number($("#ball1_2").val());
        if(sum==10){
            $("#out1").text("/");
            spare += 1;
            $("#spare").val(spare);
            total=total+sum;
            if (this.value.length >= 1) {
                $("#ball2_1").focus();
                return false;
            }
        }
        if(sum<10 && sum>=0){
            total = total+sum;
            $("#out1").text(total);
            if (this.value.length >= 1) {
                $("#ball2_1").focus();
                return false;
            }
        }
        else{
            //10이 넘을경우
            $("#ball1_1").val('');    //초기화
            $("#ball1_2").val('');   //초기화
            alert("합이10이넘었어요");
            $("#ball1_1").focus();
        }
    });

    //3frame
    $("#ball2_1").keyup(function () {
        if($("#ball2_1").val()==10) {
            $("#out2").text("X");
            strike += 1;
            $("#strike").val(strike);
            sum = Number($("#ball2_1").val());
            total = total +sum;
            var charLimit = $(this).attr("maxlength");
            if (this.value.length >= charLimit) {
                $("#ball3_1").focus();
                return false;
            }
        }
        if($("#ball2_1").val()<10) {
            if (this.value.length >= 1) {
                $("#ball2_2").focus();
                return false;
            }
        }
    });
    $("#ball2_2").keyup(function () {
        sum = Number($("#ball2_1").val()) + Number($("#ball2_2").val());
        if(sum==10){
            $("#out2").text("/");
            spare += 1;
            $("#spare").val(spare);
            total=total+sum;
            if (this.value.length >= 1) {
                $("#ball3_1").focus();
                return false;
            }
        }
        if(sum<10 && sum>=0) {
            total = total + sum;
            $("#out2").text(total);
            if (this.value.length >= 1) {
                $("#ball3_1").focus();
                return false;
            }
        }
    else{
            //10이 넘을경우
            $("#ball2_1").val('');    //초기화
            $("#ball2_2").val('');   //초기화
            alert("합이10이넘었어요");
            $("#ball2_1").focus();
        }
    });

    //4frame
    $("#ball3_1").keyup(function () {
        if($("#ball3_1").val()==10) {
            $("#out3").text("X");
            strike += 1;
            $("#strike").val(strike);
            sum = Number($("#ball3_1").val());
            total = total +sum;
            var charLimit = $(this).attr("maxlength");
            if (this.value.length >= charLimit) {
                $("#ball4_1").focus();
                return false;
            }

        }
        if($("#ball3_1").val()<10) {
            if (this.value.length >= 1) {
                $("#ball3_2").focus();
                return false;
            }
        }
    });
    $("#ball3_2").keyup(function () {
        sum = Number($("#ball3_1").val()) + Number($("#ball3_2").val());
        if(sum==10){
            $("#out3").text("/");
            spare += 1;
            $("#spare").val(spare);
            total=total+sum;
            if (this.value.length >= 1) {
                $("#ball4_1").focus();
                return false;
            }
        }
        if(sum<10 && sum>=0){
            total = total+sum;
            $("#out3").text(total);
            if (this.value.length >= 1) {
                $("#ball4_1").focus();
                return false;
            }
        }
        else{
            //10이 넘을경우
            $("#ball3_1").val('');    //초기화
            $("#ball3_2").val('');   //초기화
            alert("합이10이넘었어요");
            $("#ball3_1").focus();
        }
    });

    //5frame
    $("#ball4_1").keyup(function () {
        if($("#ball4_1").val()==10) {
            $("#out4").text("X");
            strike += 1;
            $("#strike").val(strike);
            sum = Number($("#ball4_1").val());
            total = total +sum;
            var charLimit = $(this).attr("maxlength");
            if (this.value.length >= charLimit) {
                $("#ball5_1").focus();
                return false;
            }

        }
        if($("#ball4_1").val()<10) {
            if (this.value.length >= 1) {
                $("#ball4_2").focus();
                return false;
            }
        }
    });
    $("#ball4_2").keyup(function () {
        sum = Number($("#ball4_1").val()) + Number($("#ball4_2").val());
        if(sum==10){
            $("#out4").text("/");
            spare += 1;
            $("#spare").val(spare);
            total=total+sum;
            if (this.value.length >= 1) {
                $("#ball5_1").focus();
                return false;
            }
        }
        if(sum<10 && sum>=0){
            total = total+sum;
            $("#out4").text(total);
            if (this.value.length >= 1) {
                $("#ball5_1").focus();
                return false;
            }
        }
        else{
            //10이 넘을경우
            $("#ball4_1").val('');    //초기화
            $("#ball4_2").val('');   //초기화
            alert("합이10이넘었어요");
            $("#ball4_1").focus();
        }
    });

    //6frame
    $("#ball5_1").keyup(function () {
        if($("#ball5_1").val()==10) {
            $("#out5").text("X");
            strike += 1;
            $("#strike").val(strike);
            sum = Number($("#ball5_1").val());
            total = total +sum;
            var charLimit = $(this).attr("maxlength");
            if (this.value.length >= charLimit) {
                $("#ball6_1").focus();
                return false;
            }

        }
        if($("#ball5_1").val()<10) {
            if (this.value.length >= 1) {
                $("#ball5_2").focus();
                return false;
            }
        }
    });
    $("#ball5_2").keyup(function () {
        sum = Number($("#ball5_1").val()) + Number($("#ball5_2").val());
        if(sum==10){
            $("#out5").text("/");
            spare += 1;
            $("#spare").val(spare);
            total=total+sum;
            if (this.value.length >= 1) {
                $("#ball6_1").focus();
                return false;
            }
        }
        if(sum<10 && sum>=0){
            total = total+sum;
            $("#out5").text(total);
            if (this.value.length >= 1) {
                $("#ball6_1").focus();
                return false;
            }
        }
        else{
            //10이 넘을경우
            $("#ball5_1").val('');    //초기화
            $("#ball5_2").val('');   //초기화
            alert("합이10이넘었어요");
            $("#ball5_1").focus();
        }
    });

    //7frame
    $("#ball6_1").keyup(function () {
        if($("#ball6_1").val()==10) {
            $("#out6").text("X");
            strike += 1;
            $("#strike").val(strike);
            sum = Number($("#ball6_1").val());
            total = total +sum;
            var charLimit = $(this).attr("maxlength");
            if (this.value.length >= charLimit) {
                $("#ball7_1").focus();
                return false;
            }

        }
        if($("#ball6_1").val()<10) {
            if (this.value.length >= 1) {
                $("#ball6_2").focus();
                return false;
            }
        }
    });
    $("#ball6_2").keyup(function () {
        sum = Number($("#ball6_1").val()) + Number($("#ball6_2").val());
        if(sum==10){
            $("#out6").text("/");
            spare += 1;
            $("#spare").val(spare);
            total=total+sum;
            if (this.value.length >= 1) {
                $("#ball7_1").focus();
                return false;
            }
        }
        if(sum<10 && sum>=0){
            total = total+sum;
            $("#out6").text(total);
            if (this.value.length >= 1) {
                $("#ball7_1").focus();
                return false;
            }
        }
        else{
            //10이 넘을경우
            $("#ball6_1").val('');    //초기화
            $("#ball6_2").val('');   //초기화
            alert("합이10이넘었어요");
            $("#ball6_1").focus();
        }
    });
    //8frame
    $("#ball7_1").keyup(function () {
        if($("#ball7_1").val()==10) {
            $("#out7").text("X");
            strike += 1;
            $("#strike").val(strike);
            sum = Number($("#ball7_1").val());
            total = total +sum;
            var charLimit = $(this).attr("maxlength");
            if (this.value.length >= charLimit) {
                $("#ball8_1").focus();
                return false;
            }
        }
        if($("#ball7_1").val()<10) {
            if (this.value.length >= 1) {
                $("#ball7_2").focus();
                return false;
            }
        }
    });
    $("#ball7_2").keyup(function () {
        sum = Number($("#ball7_1").val()) + Number($("#ball7_2").val());
        if(sum==10){
            $("#out7").text("/");
            spare += 1;
            $("#spare").val(spare);
            total=total+sum;
            if (this.value.length >= 1) {
                $("#ball8_1").focus();
                return false;
            }
        }
        if(sum<10 && sum>=0){
            total = total+sum;
            $("#out7").text(total);
            if (this.value.length >= 1) {
                $("#ball8_1").focus();
                return false;
            }
        }
        else{
            //10이 넘을경우
            $("#ball7_1").val('');    //초기화
            $("#ball7_2").val('');   //초기화
            alert("합이10이넘었어요");
            $("#ball7_1").focus();
        }
    });
    //9frame
    $("#ball8_1").keyup(function () {
        if($("#ball8_1").val()==10) {
            $("#out8").text("X");
            strike += 1;
            $("#strike").val(strike);
            sum = Number($("#ball8_1").val());
            total = total +sum;
            $("#ball9_1").focus();

        }
        if($("#ball8_1").val()<10) {
            if (this.value.length >= 1) {
                $("#ball8_2").focus();
                return false;
            }
        }
    });
    $("#ball8_2").keyup(function () {
        sum = Number($("#ball8_1").val()) + Number($("#ball8_2").val());
        if(sum==10){
            $("#out8").text("/");
            spare += 1;
            $("#spare").val(spare);
            total=total+sum;
            if (this.value.length >= 1) {
                $("#ball9_1").focus();
                return false;
            }
        }
        if(sum<10 && sum>=0){
            total = total+sum;
            $("#out8").text(total);
            if (this.value.length >= 1) {
                $("#ball9_1").focus();
                return false;
            }
        }
        else{
            //10이 넘을경우
            $("#ball8_1").val('');    //초기화
            $("#ball8_2").val('');   //초기화
            alert("합이10이넘었어요");
            $("#ball8_1").focus();
        }
    });

    //10frame
    $("#ball9_1").keyup(function () {
        //첫투구가 스트라이크인경우
        if($("#ball9_1").val() == 10) {
            $("#ball9_2").focus();
            strike += 1;
            $("#strike").val(strike);
            $("#ball9_2").keyup(function () {
                //두번째투구가 스트라이크인경우
                if ($("#ball9_2").val() == 10) {
                    strike += 1;
                    $("#ball9_3").focus();
                    //3번째도 스트라이크인경우
                    if ($("#ball9_3").val() == 10) {
                        strike += 1;
                        sum = Number($("#ball9_1").val()) + Number($("#ball9_2").val()) + Number($("#ball9_3").val());
                        total = total + sum;
                        if (total >=100){
                            perfectgame +=1;
                            $("#perfectgame").val(perfectgame);
                        }
                        $("#out9").text(total);
                        $("#total").val(total);

                    }
                }
                //두번째 투구가 스트라이크가 아닌경우
            });
        }
        //첫투구가 스트라이크가 아닌경우
        if($("#ball9_1").val()<10 && $("#ball9_1").val()>=0) {
            $("#ball9_2").focus();
            $("#ball9_2").keyup(function () {
                sum = Number($("#ball9_1").val()) + Number($("#ball9_2").val());
                //두번째 투구가 스페어일때
                if (sum == 10){
                    spare += 1;
                    $("#spare").val(spare);
                    $("#ball9_3").focus();
                    $("#ball9_3").keyup(function () {
                        if ($("#ball9_3").val() <= 10 && $("#ball9_3").val() > 0) {
                            if($("#ball9_3").val() == 10){
                                strike += 1;
                                $("#strike").val(strike);
                            }
                            total = total+sum + Number($("#ball9_3").val());
                            $("#out9").text(total);
                            $("#total").val(total);
                        } else {
                            $("#ball9_3").val('');    //초기화
                            alert("0~10사이를 입력해주세요");
                            $("#ball9_3").focus();
                        }
                    });
                }
                //두번째 투구가 스페어가아닐때
                if (sum <10){
                    total = total +sum;
                    $("#out9").text(total);
                    $("#total").val(total);
                }
                if(sum>10){
                    $("#ball9_1").val('');    //초기화
                    $("#ball9_2").val('');   //초기화
                    alert("합이10이넘어요");
                }
            });
        }
        else {
            $("#ball9_1").val('');    //초기화
            $("#ball9_2").val('');   //초기화
            alert("0~10사이를 입력해주세요");
            $("#ball9_1").focus();
        }
    });

});

