<#-- @ftlvariable name="post" type="cn.sixlab.six.web.vo.FullPostInfo" -->
<@FrameBody title="${post.postTitle!''}" keywords="${post.postTitle!''}" description="${post.postSummary!''}  ">

    <link rel="stylesheet" href="/static/plugins/wangeditor/wangEditor.min.css">

<div>
    <h1>${post.postTitle}</h1>
    <div>
        <span>${post.authorName}</span>
        <span>${post.postDate?string("yyyy-MM-dd HH:mm:ss")}</span>
    </div>
    <div>
        <span class="six-widget-post-categorys">
            <#assign categoryCodes=(post.categoryCodes?split(','))![] />
            <#assign categoryNames=(post.categoryNames?split(','))![] />
            <#list categoryCodes as categoryCode>
                <a class="six-widget-post-category" href="/category/${categoryCode}.html">${categoryNames[categoryCode_index]}</a>
            </#list>
        </span>
        <span class="six-widget-post-tags">
            <#assign tagCodes=(post.tagCodes?split(','))![] />
            <#assign tagNames=(post.tagNames?split(','))![] />
            <#list tagCodes as tagCode>
                <a class="six-widget-post-tag" href="/tag/${tagCode}.html">${tagNames[tagCode_index]}</a>
            </#list>
        </span>
    </div>
    <div>
        ${post.postContent}
    </div>
</div>

</@FrameBody>