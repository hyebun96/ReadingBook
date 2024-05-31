$(document).ready(function(){
    const nav_active = document.getElementsByClassName("nav_active");
    const active = document.getElementsByClassName("active");

    if (nav_active.length > 0) {
        nav_active[0].className = nav_active[0].className.replace(" nav_active", "");
    }

    if(active.length > 0){
        active[0].className = active[0].className.replace(" active", "");
    }

    const pathname = window.location.pathname;

    if(pathname === '/'){
        $("#dashboard").addClass("nav_active");
    } else if (pathname === '/book/search'){
        $("#book-toggle").addClass("open");
        $("#book-toggle").addClass("active");
        $("#bookSearch").addClass("nav_active");
    } else if (pathname === '/bookshelf/list'){
        $("#book-toggle").addClass("open");
        $("#book-toggle").addClass("active");
        $("#bookShelf").addClass("nav_active");
    } else if (pathname === '/member/profile'){
        $("#member-toggle").addClass("open");
        $("#member-toggle").addClass("active");
        $("#memberProfile").addClass("nav_active");
    }

});

function myPage() {
    location.href='/member/profile';
}

function info() {
    location.href='/intobooks/info';
}

