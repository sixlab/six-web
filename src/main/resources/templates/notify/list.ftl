<#include "/ms/frame.ftl"/>
<@AdminFrame title="提醒配置列表">

<div id="content">
    <div class="data-op">
        <a class="btn btn-outline-success my-2 my-sm-0" href="/six/notify/add">添加</a>
    </div>

    <table class="table table-striped data-table">
        <thead class="thead-dark">
        <tr>
            <th scope="col" width="10%"></th>
            <th scope="col" width="20%">编号</th>
            <th scope="col" width="20%">类型</th>
            <th scope="col" width="20%">目标</th>
            <th scope="col" width="10%">状态</th>
            <th scope="col" width="20%">操作</th>
        </tr>
        </thead>
        <tbody>
        <#list dataList as item>
            <tr>
                <td>${item_index+1}</td>
                <td>${item.code!""}</td>
                <td>
                    <#if item.type==0>
                        <#if (item.rise>0)>
                            升幅
                        <#elseif (item.rise<0)>
                            降幅
                        </#if>
                    <#elseif item.type==1>
                        上限
                    <#elseif item.type==-1>
                        下限
                    </#if>
                </td>
                <td>${(item.rise?string("0.00"))!''}</td>
                <td>${item.status!""}</td>
                <td>
                    <button data-id="${item.id}" class="btn btn-outline-danger my-2 my-sm-0 delete">删除</button>
                    <a target="_blank" class="btn btn-outline-info my-2 my-sm-0" href="/six/notify/edit/${item.id}">修改</a>
                </td>
            </tr>
        </#list>
        </tbody>
    </table>
</div>

<script type="text/javascript">
    $(function(){
        $(".delete").click(function () {
            $.ajax({
                url:"/six/notify/delete/"+$(this).data("id"),
                method:"post",
                dataType:"json",
                success:function(data){
                    alert(JSON.stringify(data));
                    location.reload();
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
