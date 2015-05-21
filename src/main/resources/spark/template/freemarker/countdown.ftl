<!doctype HTML>
<head>
<link rel="stylesheet" href="/css/bootstrap.min.css">
<link rel="stylesheet" href="/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="/css/grid.css">

<script src="/js/jquery-2.1.4.js"></script>
<script src="/js/bootstrap.min.js"></script>
</head>
<body>
<h1>Solutions</h1>



<ul>
<#list wordList! as word>
<li>${word}
</#list>
</ul>
 
<#if error??>
<div class="error">
<h3>Error</h3>
${error}
</div>
</#if>
</body>