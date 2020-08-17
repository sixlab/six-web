<#-- @ftlvariable name="pageInfo" type="com.github.pagehelper.PageInfo" -->
<#-- @ftlvariable name="pageInfo.list" type="cn.sixlab.six.web.models.PostInfo[]" -->
<#include "/ms/frame.ftl"/>
<@AdminFrame title="文章列表">

    <div id="content">
        <div class="data-op">
            <a class="btn btn-outline-success my-2 my-sm-0" href="/six/post/add">添加</a>
        </div>

        <table class="table table-striped data-table">
            <thead class="thead-dark">
            <tr>
                <th scope="col" width="10%"></th>
                <th scope="col" width="20%">标题</th>
                <th scope="col" width="20%">类型</th>
                <th scope="col" width="20%">时间</th>
                <th scope="col" width="10%">状态</th>
                <th scope="col" width="20%">操作</th>
            </tr>
            </thead>
            <tbody>
            <#list pageInfo.list as item>
                <tr>
                    <td>${item_index+1}</td>
                    <td>${item.postTitle!""}</td>
                    <td>${item.postType!""}</td>
                    <td>${(item.postDate?string("yyyy-MM-dd HH:mm:ss"))!''}</td>
                    <td>${item.postStatus!""}</td>
                    <td>
                        <button data-id="${item.id}" class="btn btn-outline-danger my-2 my-sm-0 delete">删除</button>
                        <#if 'open' != item.postStatus>
                            <button data-id="${item.id}" class="btn btn-outline-success my-2 my-sm-0 publish">发布</button>
                        </#if>
                        <a target="_blank" class="btn btn-outline-info my-2 my-sm-0" href="/six/post/edit/${item.id}">修改</a>
                    </td>
                </tr>
            </#list>
            </tbody>
        </table>

        <@AdminPager link="/six/post/list" />
    </div>

    <script type="text/javascript">
        $(function(){
            $(".delete").click(function () {
                $.ajax({
                    url:"/six/post/delete/"+$(this).data("id"),
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
            $(".publish").click(function () {
                $.ajax({
                    url:"/six/post/publish/"+$(this).data("id"),
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
