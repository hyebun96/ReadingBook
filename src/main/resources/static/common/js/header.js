$(document).ready(function(){
    const current = document.getElementsByClassName("nav_active");

    if (current.length > 0) {
        current[0].className = current[0].className.replace(" nav_active", "");
    }

    const pathname = window.location.pathname;

    if(pathname === '/'){
        $("#dashboard").addClass("nav_active");
    } else if (pathname === '/book/search'){
        $("#book-toggle").addClass("open");
        $("#bookSearch").addClass("nav_active");
    } else if (pathname === '/bookshelf/list'){
        $("#book-toggle").addClass("open");
        $("#bookShelf").addClass("nav_active");
    } else if (pathname === '/member/profile'){
        $("#member-toggle").addClass("open");
        $("#memberProfile").addClass("nav_active");
    }

});

function myPage() {
    location.href='/member/profile';
}

function info() {
    location.href='/intobooks/info';
}

