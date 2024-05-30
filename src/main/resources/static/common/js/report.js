document.addEventListener('DOMContentLoaded', function () {
    const averageScopeDisplay = document.getElementById('averageScope');
    const radioInputs = document.querySelectorAll('input[name="scope"]');

    // 저장된 평균 별점
    const scopeVal = document.querySelector('input[name="scope"]:checked');
    if (scopeVal) {
        averageScopeDisplay.textContent = `${scopeVal.value}점`;
    }

    // 라디오 버튼에 대한 변경 이벤트 리스너 추가
    radioInputs.forEach(function (input) {
        input.addEventListener('change', function () {
            // 변경된 라디오 버튼 값 가져오기
            const checkedInput = document.querySelector('input[name="scope"]:checked');

            // 평균 별점 업데이트
            if (checkedInput) {
                averageScopeDisplay.textContent = `${checkedInput.value}점`;
            }
        });
    });
});

const scopeInputs = document.querySelectorAll('input[name="scope"]');
const averageScopeDisplay = document.getElementById('averageScope');

scopeInputs.forEach(input => {
    input.addEventListener('change', () => {
        averageScopeDisplay.textContent = `${input.value}점`;
    });
});

validateForm = () => {
    const selectedScope = document.querySelector('input[name="scope"]:checked');
    if (!selectedScope) {
        swal.fire({
            title: "⭐️ 별점은 필수입니다! ⭐️",
            icon: "error"
        });
        return false;
    }
    return true;
}

function deleteBookAndReport(id, title, author) {

    swal.fire({
        title: "책장에서 " + author +  "의 ⌜" + title + "⌟ 를 삭제하시겠습니까?",
        text: "삭제하시면 지금까지 작성한 독서록 기록도 사라집니다",
        icon: "question",
        confirmButtonText: "네",
        cancelButtonText: "아니오",
        showCancelButton: true,
        showCloseButton: true
    }).then((result) => {
        if(result.isConfirmed){
            const formHtml = `<form id="deleteForm" action="/report/delete/${id}" method = "post">
                      <input type="hidden" th:field="*{id}" />
                      </form>`;
            const doc = new DOMParser().parseFromString(formHtml, 'text/html');
            const form = doc.body.firstChild;

            document.body.append(form);
            document.getElementById('deleteForm').submit();
        }
    });
}

function deleteBook(id, title, author) {

    swal.fire({
        title: "책장에서 " + author +  "의 ⌜" + title + "⌟를 삭제하시겠어요?",
        icon: "question",
        confirmButtonText: "네",
        cancelButtonText: "아니오",
        showCancelButton: true,
        showCloseButton: true
    }).then((result) => {
        if(result.isConfirmed){
            const formHtml = `<form id="deleteForm" action="/bookshelf/delete/${id}" method = "post">
                      <input type="hidden" th:field="*{id}" />
                      </form>`;
            const doc = new DOMParser().parseFromString(formHtml, 'text/html');
            const form = doc.body.firstChild;

            document.body.append(form);
            document.getElementById('deleteForm').submit();
        }
    });

}