<#macro FrameHeader title=''>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <#if title??><title>${title} ${siteInfo.titleSeparator!'-'} ${siteInfo.siteName!'sixlab'}</title><#else><title>${siteInfo.siteName!'sixlab'}</title></#if>
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel="stylesheet" href="/static/plugins/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/static/css/frame.css">
    <script src="/static/plugins/jquery/jquery.min.js"></script>
</head>
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

<#macro FrameBody title>
<@FrameInfo>
<!DOCTYPE HTML>
<html lang='zh-CN'>
<@FrameHeader title='${title}'/>
<body class="d-flex flex-column h-100 body-wrapper">
<header class="header-wrapper">
    <!-- Fixed navbar -->
    <nav class="navbar navbar-expand-md navbar-dark fixed-top">
        <a class="navbar-brand" href="#">Fixed navbar</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarCollapse">
            <@Menu position="test"/>
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
</body>
</html>
</@FrameInfo>
</#macro>
