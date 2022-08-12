function saveComment() {
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
    }).fail(function (error) {
        alert(JSON.stringify(error));
    });
}

function deleteComment(commentId) {
    let ans = confirm('삭제하시겠습니까?');
    if (ans) {
        const boardId = $('#boardId').val()
        $.ajax({
            type: 'DELETE',
            url: '/comment/delete/' + commentId,
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(boardId),
        }).done(function (frag) {
            alert('댓글 삭제되었습니다.');
            $('#commentContainer').replaceWith(frag);
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    }
}
function updateComment(commentId){
    let ans = confirm('수정하시겠습니까?');
    if(ans){
        const data = {
            comment: $('#comment-content'+commentId).val()
        }

        $.ajax({
            type: 'PUT',
            url: '/comment/update/'+commentId,
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data),
        }).done(function(frag){
            alert('댓글 수정되었습니다.');
            $('#commentContainer').replaceWith(frag);
        }).fail(function(error){
           alert(JSON.stringify(error));
        });
    }
}

function update(id){
    document.getElementById("comment-view"+id).style.display ='none';
    document.getElementById("comment-update"+id).style.display ='block';
}

function updateCancel(id){
    document.getElementById("comment-view"+id).style.display='block';
    document.getElementById("comment-update"+id).style.display='none';
}