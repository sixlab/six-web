<@FrameBody title="所有榜单">

<#list dataList as item>

    <#if item.groupType == 'group' >
        <div class="card card-space">
            <div class="card-header">
                ${item.groupName}
            </div>
            <div class="card-body">
    <#elseif item.groupType == 'item' >
                <a href="/rank/${item.id}.html" class="btn btn-primary">${item.groupName}</a>
    </#if>

    <#if (listSize == (item_index+1)) || dataList[item_index+1].groupType == 'group' >
            </div>
        </div>
    </#if>

</#list>

</@FrameBody>