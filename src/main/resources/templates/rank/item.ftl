<#-- @ftlvariable name="dataList" type="java.util.List<cn.sixlab.six.web.models.RankItem>" -->
<#-- @ftlvariable name="group" type="cn.sixlab.six.web.models.RankGroup" -->

<@FrameBody title="${group.groupName}排行榜" keywords="${group.groupName}" description="${group.groupName}">

    <table class="table table-striped">
        <thead class="thead-dark">
        <tr>
            <th scope="col" width="10%">排名</th>
            <th scope="col" width="25%">名字</th>
            <th scope="col" width="50%">相关链接</th>
            <th scope="col" width="15%">热度</th>
        </tr>
        </thead>
        <tbody>
        <#list dataList as item>
        <tr>
            <th scope="row">${item.itemRank}</th>
            <td>${item.itemName}</td>
            <td>
                <a target="_blank" href="https://baike.baidu.com/item/${item.itemName}">简介</a>
                <a target="_blank" href="http://news.baidu.com/ns?word=${item.itemName}">新闻</a>
                <a target="_blank" href="https://image.baidu.com/search/index?tn=baiduimage&word=${item.itemName}">图片</a>
            </td>
            <td>
                <#if (item.itemChange>0)>
                    <span class="badge badge-danger badge-pill">↑${item.itemHit}</span>
                <#elseif (item.itemChange<0)>
                    <span class="badge badge-success badge-pill">↓${item.itemHit}</span>
                <#else>
                    <span class="badge badge-primary badge-pill">-${item.itemHit}</span>
                </#if>
            </td>
        </tr>
        </#list>
        </tbody>
    </table>

</@FrameBody>