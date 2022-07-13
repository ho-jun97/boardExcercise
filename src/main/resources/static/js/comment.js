var comment = {
    init : function (){
        var _this = this;
        $('$btn-comment-delete').on('click', function(){
           _this.delete();
        });
    },
    delete : function(){
        var commentId = $('#id').val();


        $.ajax({
            type: 'DELETE',
            url: '/board/comment/delete'+commentId,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8'
        }).done(function (){
            alert('글이 삭제되었습니다.')
            window.location.href='/';
        }).fail(function (error){
            alert(JSON.stringify(error));
        })
    }
};

comment.init();