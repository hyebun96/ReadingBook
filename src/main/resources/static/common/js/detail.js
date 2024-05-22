function existMessageModal(existMessage, message, bookshelfId) {

    if(existMessage){
        swal.fire({
            title:  message + "<br>독후감으로 바로 이동하시겠습니까?",
            icon: "success",
            confirmButtonText: "네",
            cancelButtonText: "아니오",
            showCancelButton: true,
            showCloseButton: true
        }).then((result) => {
            if(result.isConfirmed){
                location.href = "/report/form?id=" + bookshelfId
            }
        });
    }
}

function save() {

    const isbn = document.getElementById("isbn").value

    saveBook(isbn).then(
        result => {
            const existMessage = result.data.existMessage;
            const message = result.data.message;
            const bookshelfId = result.data.bookshelfId;

            existMessageModal(existMessage, message, bookshelfId)
        }
    ).catch(e => {
        console.log(e)
    })
}

async function saveBook(isbn) {
    const result = await axios.get(`/api/save/`+ isbn)
    return result;
}