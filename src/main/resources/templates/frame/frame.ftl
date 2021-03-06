
<#macro FrameBody title="" keywords="" description="">
<@SiteInfo>
<!DOCTYPE HTML>
<html lang='zh-CN'>
<@FrameHeader title='${title}' keywords='${keywords}' description='${description}'/>
<body class="d-flex flex-column h-100 body-wrapper">
<header class="header-wrapper">
    <!-- Fixed navbar -->
    <nav class="navbar navbar-expand-md navbar-dark fixed-top">
        <a class="navbar-brand" href="/">${siteInfo.siteName}</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarCollapse">
            <@Menu position="six"/>
            <form class="form-inline mt-2 mt-md-0">
                <input class="form-control mr-sm-2" type="text" placeholder="Search" aria-label="Search">
                <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
            </form>
        </div>
    </nav>
</header>

<!-- Begin page content -->
<main role="main" class="flex-shrink-0 content-wrapper">
    <div class="container">
        <#nested />
    </div>
</main>

<footer class="footer mt-auto py-3 footer-wrapper">
    <div class="container">
        <span class="text-muted">
            <strong>Copyright &copy; ${siteInfo.copyrightYear!'2020'} <a href="/">${siteInfo.siteName!'sixlab'}</a>.</strong> All rights reserved.
        </span>
    </div>
</footer>

<script src="/static/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
<script src="/static/js/frame.js"></script>
<#--    <script src="/static/plugins/vue/vue.min.js"></script>-->
<script>
    (function(){
        var bp = document.createElement('script');
        var curProtocol = window.location.protocol.split(':')[0];
        if (curProtocol === 'https') {
            bp.src = 'https://zz.bdstatic.com/linksubmit/push.js';
        }
        else {
            bp.src = 'http://push.zhanzhang.baidu.com/push.js';
        }
        var s = document.getElementsByTagName("script")[0];
        s.parentNode.insertBefore(bp, s);
    })();
</script>
</body>
</html>
</@SiteInfo>
</#macro>

<#macro Menu position>
    <@MenuInfo position='${position}'>
        <ul class="navbar-nav mr-auto">
            <#assign totalNum = menuInfo?size />
            <#list menuInfo as item>
                <#assign nextIndex = (item_index+1) />
                <#assign nextLevel = 0 />
                <#assign hasSub = false />
                <#assign hasNext = (nextIndex<totalNum) />
                <#if hasNext>
                    <#assign nextLevel = menuInfo[nextIndex].menuLevel />
                    <#assign hasSub = (item.menuLevel == 1 && nextLevel == 2) />
                </#if>
                <#if item.menuLevel == 1 >
                    <#if hasSub>
                        <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="${item.menuPath}" id="dropdown-${item.id}" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">${item.menuName}</a>
                    <div class="dropdown-menu" aria-labelledby="dropdown-${item.id}">
                    <#else>
                        <li class="nav-item">
                            <a class="nav-link" href="${item.menuPath}">${item.menuName}</a>
                        </li>
                    </#if>
                <#elseif item.menuLevel == 2 >
                    <a class="dropdown-item" href="${item.menuPath}">${item.menuName}</a>
                    <#if !hasNext || ( nextLevel == 1 )>
                        </div>
                        </li>
                    </#if>
                </#if>
            </#list>
        </ul>
    </@MenuInfo>
</#macro>

<#macro FrameHeader title='' keywords='' description=''>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title><#if title??>${title} ${siteInfo.titleSeparator!'-'} </#if>${siteInfo.siteName!''}</title>
        <meta name="keywords" content="${keywords!''},${siteInfo.keywords!''}" />
        <meta name="description" content="${siteInfo.description!''}${description!''}" />
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="/static/plugins/bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" href="/static/css/frame.css">
        <script src="/static/plugins/jquery/jquery.min.js"></script>
    </head>
</#macro>

<#macro PostListWidget postType='' attrIds='' pageNum='' pageSize=''>
    <div class="six-widget-post-list">
        <@PostListInfo postType='${postType}' attrIds='${attrIds}' pageNum='${pageNum}' pageSize='${pageSize}'>
            <div class="six-widget-post-wrapper">
                <#list postList.list as item>
                    <div class="six-widget-post-item six-widget-post-item-${item.id}">
                        <a class="six-widget-post-link" href="/post/${item.aliasName}.html">${item.postTitle}</a>
                        <span class="six-widget-post-author">${item.authorName}</span>
                        <span class="six-widget-post-date">${item.postDate?string("yyyy-MM-dd HH:mm:ss")}</span>
                        <span class="six-widget-post-summary">${item.postSummary}</span>
                        <span class="six-widget-post-categorys">
                            <#assign categoryCodes=(item.categoryCodes?split(','))![] />
                            <#assign categoryNames=(item.categoryNames?split(','))![] />
                            <#list categoryCodes as categoryCode>
                                <a class="six-widget-post-category" href="/category/${categoryCode}.html">${categoryNames[categoryCode_index]}</a>
                            </#list>
                        </span>
                        <span class="six-widget-post-tags">
                            <#assign tagCodes=(item.tagCodes?split(','))![] />
                            <#assign tagNames=(item.tagNames?split(','))![] />
                            <#list tagCodes as tagCode>
                                <a class="six-widget-post-tag" href="/tag/${tagCode}.html">${tagNames[tagCode_index]}</a>
                            </#list>
                        </span>
                    </div>
                </#list>
            </div>
            <div class="six-widget-pager-wrapper">
                
            </div>
        </@PostListInfo>
    </div>
</#macro>

