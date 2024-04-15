async function uploadMemberImg() {

    const formObj = new FormData();

    const fileInput = document.querySelector("input[name='files']");

    const files = fileInput.files;

    formObj.append("files", files[0]);

    uploadToServer(formObj)
    .catch(e => {
        console.log(e)
    })

}

async function uploadToServer(formObj) {

    const axiosConfig = {
        headers:{
            "Content-Type": "application/x-www-form-urlencoded"
        }
    }

    axios.post('/profile/uploadMemberImg', formObj, axiosConfig).then(
        result => {
            const imgSrc = result.data.imgsrc
            console.log("member->" + imgSrc)
            // document.getElementById("memberImg").src = imgSrc

            if(result.data.img === true){
                Swal.fire({
                    icon: "success",
                    title: "이미지가 정상적으로 변경되었습니다.",
                    confirmButtonText: "확인"
                }).then((result) => {
                    if(result.isConfirmed){
                        sleep(5000);

                        location.href = "/"
                    }
                });
            }
        })
        .catch((error) => {
            console.log(error)
        })
}

// 동기 지연 함수
function sleep(ms) {
    var start = Date.now() + ms;
    while (Date.now() < start) {}
}

async function returnMemberImg() {

    const axiosConfig = {
        headers:{
            "Content-Type": "application/x-www-form-urlencoded"
        }
    }

    Swal.fire({
        icon: "question",
        title: "프로필 이미지를 기존 이미지(카카오 프로필) 되돌리시겠습니까?",
        confirmButtonText: "네",
        cancelButtonText: "아니오",
        showCancelButton: true,
        showCloseButton: true
    }).then((result) => {
        if(result.isConfirmed){
            axios.post('/profile/return', axiosConfig).then(
                result => {
                    const imgSrc = result.data.result
                    console.log("result->" + result)

                    if(result.data.result === true){
                        Swal.fire({
                            icon: "success",
                            title: "이미지가 정상적으로 변경되었습니다.",
                            confirmButtonText: "확인"
                        }).then((result) => {
                            if(result.isConfirmed){
                                sleep(5000);

                                location.href = "/"
                            }
                        });
                    }
                })
                .catch((error) => {
                    console.log(error)
                })
        }
    });



}