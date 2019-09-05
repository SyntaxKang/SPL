function join() {

   if(document.join.password.value==" "){
        alert("필수사항입니다.");
        document.join.password.focus();
        return false;
    }
       else if(document.join.nickname.value==" "){
        alert("필수사항입니다.");
        document.join.nickname.focus();
        return false;
    }
    else if(document.join.areaa.value==" "){
        alert("필수사항입니다.");
        document.join.areaa.focus();
        return false;
    }
    return true;

}