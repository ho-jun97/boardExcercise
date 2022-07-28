const main = {
    init: function () {
        const _this = this;
        $('#btn-comment-save').on('click', function () {
            _this.save();
        });
    },
    save: function () {
        alert('댓글 등록 요청됨');
        const data = {
            comment: $('#comment').val()
        }
        const boardId = $('#boardId').val()
        $.ajax({
            type: 'POST',
            url: '/comment/save/' + boardId,
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data),
        }).done(function (frag) {
            alert('댓글이 등록되었습니다.');
            $('#commentContainer').replaceWith(frag);
        }).fail(function(error){
            alert(JSON.stringify(error));
        });
    }
}
// function deleteComment(boardId, commentId) {
//     let ans = confirm(commentId + '삭제하시겠습니까?');
//     if (ans) {
//         $.ajax({
//             type: 'DELETE',
//             url: '/comment/delete/' + commentId,
//             contentType: 'application/json; charset=utf-8',
//             success: function () {
//                 alert("URL 접속 성공");
//             }
//         }).done(function (response) {
//             alert(response);
//             window.location.href = '/boardDetail/' + boardId;
//         }).fail(function (error) {
//             alert(JSON.stringify(error));
//         });
//     }
// }
main.init();