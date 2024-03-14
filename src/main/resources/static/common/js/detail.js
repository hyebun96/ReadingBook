$(function() {
    const existMessage =  $("#existMessage").val();

    if(existMessage == ""){
        modal_off();
    } else {
        modal_on()
    }
});

function modal_off() {
    $(".modal-dialog").hide();
    $(".modal-dialog").css("display", "none");
}

function modal_on() {
    $(".modal-dialog").show();
    $(".modal-dialog").css("display", "block");
}