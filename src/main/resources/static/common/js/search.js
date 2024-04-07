async function moreList(title, total, start) {
    const result = await axios.get(`/api/search`, {params: {title, total, start}})

    return result;
}

function bookDetail(isbn) {
    location.href='/book/detail/' + isbn;
}

function list() {

    const title = document.getElementById("title").value
    const total = document.getElementById("total").value
    const start = document.getElementById("start").value

    moreList(title, total, start).then(
        result => {
            document.getElementById("title").value = result.data.title
            insertReposeValue(result.data.pageResponseDTO)
            printList(result.data.searchBookList)
        }
    ).catch( e => {
        console.log(e)
    })
}

function insertReposeValue(pageResponseDTO) {
    if(pageResponseDTO.next === false){
        document.getElementById("next").style.display = "none"
    } else {
        document.getElementById("page").innerHTML = pageResponseDTO.page
        document.getElementById("total").value = pageResponseDTO.total
        document.getElementById("start").value = pageResponseDTO.nextStart
    }
}

function printList(searchBookList) {
    let listStr = '';

    for(let i in searchBookList) {

        listStr += `<img class="book-img-size" 
             src="`+  searchBookList[i].image  +`"
             value="`+  searchBookList[i].isbn  +` "
             onclick="bookDetail(`+  searchBookList[i].isbn  + ` )">
            `
    }

    document.getElementById("book-img").innerHTML += listStr
}

