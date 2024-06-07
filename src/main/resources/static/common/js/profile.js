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
            "Content-Type": "multipart/form-data"
        }
    }

    axios.post('/profile/uploadMemberImg', formObj, axiosConfig).then(
        result => {
            const img = result.data.img
            console.log("img->" + img)

            if(result.data.img === true){

                const mask = document.querySelector('.mask');
                const html = document.querySelector('html');

                mask.style.display = "block";
                html.style.overflow = 'hidden'; //로딩 중 스크롤 방지

                setTimeout( function () {

                    mask.style.opacity = '0'; //서서히 사라지는 효과
                    html.style.overflow = 'auto'; //스크롤 방지 해제
                    mask.style.display = 'none';

                    Swal.fire({
                        icon: "success",
                        title: "이미지가 정상적으로 변경되었습니다.",
                        confirmButtonText: "확인"
                    }).then((result) => {
                        if(result.isConfirmed) {
                            // location.reload();
                        }
                    });
                }, 15000);
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
            "Content-Type": "multipart/form-datas"
        }
    }

    Swal.fire({
        icon: "question",
        title: "현재 프로필 이미지를 기존 이미지(카카오 프로필)<br> 되돌리시겠습니까?",
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
                                location.href = "/member/profile"
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