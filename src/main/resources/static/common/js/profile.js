async function uploadMemberImg() {

    const formObj = new FormData();

    const fileInput = document.querySelector("input[name='files']");

    const files = fileInput.files;

    formObj.append("files", files[0]);

    console.log(formObj);
    console.log(fileInput.files);

    uploadToServer(formObj).then(result => {
        console.log(result)
    }).catch(e => {
        console.log(e)
    })

}

async function uploadToServer(formObj) {

    console.log("upload to server...")
    console.log(formObj)

    const axiosConfig = {
        headers:{
            "Content-Type": "application/x-www-form-urlencoded"
        }
    }

    axios.post('/uploadMemberImg', formObj, axiosConfig)
        .then((res) => {
            console.log(res)
        })
        .catch((error) => {
            console.log(error.response)
        })
}