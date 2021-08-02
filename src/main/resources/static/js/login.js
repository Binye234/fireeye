const labels = document.querySelectorAll('.form-control label')

labels.forEach(label => {
    label.innerHTML = label.innerText
        .split('')
        .map((letter, idx) => `<span style="transition-delay:${idx * 50}ms">${letter}</span>`)
        .join('')
})

$("#bottom-text").click(function () {
    $.ajax({
        type: "post",
        url: "/loginCheck",
        data: "username=" + $("#username").val() + "&password=" + $("#pwd").val() + "&code=" + $("#code").val(),
        success: function (data) {
            $('#msg').empty();   //清空Text里面的所有内容
            if (data.code == "200") {
                $(location).attr('href',data.msg);
            } else if (data.code == "301") {
                $("#msg").text(data.msg);
            } else if (data.code == "302") {
                $("#msg").text(data.msg);
            }
        }
    });
})
// 刷新验证码
$("#verificationCodeImg").click(function () {
    $(this).hide().attr('src', '/code?').fadeIn();
});

$("body").keydown(function () {
    if(event.keyCode == "13"){
        $("#bottom-text").click();
    }
})