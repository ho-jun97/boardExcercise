function deleteComment(boardId, commentId){
    $.ajax({
        type: 'DELETE',
        url: '/board/comment/delete/' + commentId,
        dataType: 'json',
        contentType: 'application/json; charset=utf-8'
    }).done(function () {
        alert('글이 삭제되었습니다.')
        window.location.href = '/';
    }).fail(function (error) {
        alert(JSON.stringify(error));
    });
}