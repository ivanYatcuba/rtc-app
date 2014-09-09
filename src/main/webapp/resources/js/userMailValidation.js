function addMailValidation(url){
    if($("#email").length){
        $("#email").rules('add', {
            remote: {
                url: url,
                type: "post",
                data : {email: function(){return $("#email").val();}}
            },
            messages: {
                remote: "Email already exist!"
            }
        });
    }
}