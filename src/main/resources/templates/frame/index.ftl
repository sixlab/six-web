<#macro FrameHeader title=''>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <#if title??><title>${title} ${siteInfo.titleSeparator!'-'} ${siteInfo.siteName!'sixlab'}</title><#else><title>${siteInfo.siteName!'sixlab'}</title></#if>
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel="stylesheet" href="/static/plugins/bootstrap/css/bootstrap.min.css">
</head>
</#macro>

<#macro FrameBody title>
<@FrameInfo>
    <!DOCTYPE HTML>
    <html lang='zh-CN'>
    <@FrameHeader title='${title}'/>
    <body>
    <div class="body-wrapper">
        <header class="header-wrapper">

        </header>

        <div class="content-wrapper">
            <#nested />
        </div>

        <footer class="footer-wrapper">
            <strong>Copyright &copy; ${siteInfo.copyrightYear!'2020'} <a href="/">${siteInfo.siteName!'sixlab'}</a>.</strong> All rights reserved.
        </footer>

    </div>

    <script src="/static/plugins/jquery/jquery.min.js"></script>
    <script src="/static/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
    <script src="/static/plugins/vue/vue.min.js"></script>
    </body>
    </html>
</@FrameInfo>
</#macro>
