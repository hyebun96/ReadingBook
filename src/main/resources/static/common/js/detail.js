$(function() {
    const existMessage =  $("#existMessage").val();

    existMessageModal(existMessage)
});

function existMessageModal(existMessage) {
    if(existMessage == ""){
        modal_off();
    } else {
        modal_on()
    }
}

function modal_off() {
    $(".modal-dialog").hide();
    $(".modal-dialog").css("display", "none");
}

function modal_on() {
    $(".modal-dialog").show();
    $(".modal-dialog").css("display", "block");
}

function save() {

    const isbn = document.getElementById("isbn").value

    saveBook(isbn).then(
        result => {
            document.getElementById("existMessage").value = result.data.existMessage;
            document.getElementById("message").innerText = result.data.message;

            existMessageModal(result.data.existMessage)
        }
    ).catch(e => {
        console.log(e)
    })

}

async function saveBook(isbn) {
    const result = await axios.get(`/api/save/`+ isbn)

    return result;
}