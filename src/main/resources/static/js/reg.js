function check() {
    if(document.reg.email.value==" "){
        alert("필수사항입니다.");
        document.reg.email.focus();
        return false;
    }
    else if(document.reg.password.value==" "){
        alert("필수사항입니다.");
        document.reg.password.focus();
        return false;
    }
    else if(document.reg.name.value==" "){
        alert("필수사항입니다.");
        document.reg.name.focus();
        return false;
    }
    else if(document.reg.nickname.value==" "){
        alert("필수사항입니다.");
        document.reg.nickname.focus();
        return false;
    }
    else if(document.reg.areaa.value==" "){
        alert("필수사항입니다.");
        document.reg.areaa.focus();
        return false;
    }
    return true;

}