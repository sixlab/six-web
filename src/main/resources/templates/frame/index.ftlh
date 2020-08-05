<#macro FrameHeader title=''>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <#if title??><title>${title} - Minesite</title><#else><title>Minesite</title></#if>
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel="stylesheet" href="/api/static/plugins/bootstrap/css/bootstrap.min.css">
</head>
</#macro>



<#macro FrameBody title=springMacroRequestContext.getMessage('page.title.index')>
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
            <strong>Copyright &copy; 2020 <a href="https://minesosft.tech">minesosft</a>.</strong> All rights reserved.
        </footer>

    </div>

    <script src="/api/static/plugins/jquery/jquery.min.js"></script>
    <script src="/api/static/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
    <script src="/api/static/plugins/vue/vue.min.js"></script>
</body>
</html>
</#macro>

