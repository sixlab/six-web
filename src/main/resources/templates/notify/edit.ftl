<#include "/ms/frame.ftl"/>
<#assign title = (job??)?string('修改','新增')+'提醒配置' />
<@AdminFrame title="${title}">

<div id="content">
    <form id="editFrm">
        <input type="hidden" name="id" id="id" value="${(data.id)!''}">
        <div class="form-group">
            <label for="code">编号</label>
            <input type="text" class="form-control" name="code" id="code" value="${(data.code)!''}">
        </div>
        <div class="form-group">
            <label for="type">类型</label>
            <input type="text" class="form-control" name="type" id="type" value="${(data.type)!''}">
        </div>
        <div class="form-group">
            <label for="rise">目标值</label>
            <input type="text" class="form-control" name="rise" id="rise" value="${(data.rise?string("0.00"))!''}">
        </div>
        <div class="form-group">
            <label for="status">状态</label>
            <input type="text" class="form-control" name="status" id="status" value="${(data.status)!''}">
        </div>
        <div class="form-group" id="btnGroup">
            <a class="btn btn-secondary" href="/notify/list">返回</a>
            <button type="submit" class="btn btn-primary" id="editSubmit">提交</button>
        </div>
    </form>
</div>

<script type="text/javascript">
    $(function(){
        $("#editSubmit").click(function () {
            $.ajax({
                url:"/notify/submit",
                method:"post",
                dataType:"json",
                data:$("#editFrm").serialize(),
                success:function(data){
                    alert(JSON.stringify(data));
                },
                error:function(err){
                    alert("err");
                }
            });

            return false;
        });
    });
</script>

</@AdminFrame>
