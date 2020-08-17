<#-- @ftlvariable name="data" type="cn.sixlab.six.web.vo.FullPostInfo" -->
<#include "/ms/frame.ftl"/>
<#assign title = (data??)?string('修改','新增')+'文章' />
<@AdminFrame title="${title}">
<div id="content">
    <form id="editFrm">
        <input type="hidden" name="id" id="id" value="${(data.id)!''}">
        <div class="form-group">
            <label for="postTitle">标题</label>
            <input type="text" class="form-control" name="postTitle" id="postTitle" value="${(data.postTitle)!''}">
        </div>
        <div class="form-group">
            <label for="aliasName">链接标识</label>
            <input type="text" class="form-control" name="aliasName" id="aliasName" value="${(data.aliasName)!''}">
        </div>
        <div class="form-group">
            <label for="authorName">作者</label>
            <input type="text" class="form-control" name="authorName" id="authorName" value="${(data.authorName)!''}">
        </div>
        <div class="form-group">
            <label for="postType">文章类型</label>
            <input type="text" placeholder="archive/page/news" class="form-control" name="postType" id="postType" value="${(data.postType)!''}">
        </div>
        <div class="form-group">
            <label for="postSummary">摘要</label>
            <input type="text" class="form-control" name="postSummary" id="postSummary" value="${(data.postSummary)!''}">
        </div>
        <div class="form-group d-none">
            <label for="postContent">文章内容</label>
            <textarea name="postContent" id="postContent">${(data.postContent)!''}</textarea>
        </div>
        <div class="form-group">
            <label for="postInputDate">时间</label>
            <input type="hidden" name="postDate" id="postDate">
            <input type="text" class="form-control" name="postInputDate" id="postInputDate" value="${(data.postDate?string("yyyy-MM-dd HH:mm:ss"))!''}">
        </div>
        <div class="form-group">
            <div id="editor">
                ${(data.postContent)!''}
            </div>
        </div>
        <div class="form-group" id="btnGroup">
            <a class="btn btn-secondary" href="/six/post/list">返回</a>
            <button type="submit" class="btn btn-primary" id="editSubmit">提交</button>
        </div>
    </form>
</div>

<script src="/static/plugins/wangeditor/wangEditor.min.js"></script>
<script src="/static/plugins/moment/moment.min.js"></script>
<script type="text/javascript">
    var editor = new window.wangEditor('#editor');
    editor.create();

    $(function(){
        $("#editSubmit").click(function () {
            $("#postContent").val(editor.txt.html());

            var postSummary = $("#postSummary").val().trim();

            if(!postSummary){
                var text = editor.txt.text();
                if(text.length > 110){
                    text = text.substr(0, 100);
                }
                $("#postSummary").val(text);
            }

            var postDate = $("#postInputDate").val().trim();
            if(!postDate){
                $("#postDate").val(moment("20111031", "YYYYMMDD").valueOf());
            }

            $.ajax({
                url:"/six/post/submit",
                method:"post",
                dataType:"json",
                data:$("#editFrm").serialize(),
                success:function(data){
                    alert(JSON.stringify(data));
                },
                error:function(err){
                    alert("err");
                    console.log(err);
                }
            });

            return false;
        });
    });
</script>

</@AdminFrame>
