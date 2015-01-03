<!doctype HTML>
<head>
</head>
<body>
<h1>Links from ${subreddit}</h1>
<#list listoflinks! as link>
<div> <img src="${link.getThumb()}"> <a href="${link.getUrl()}">${link.getTitle()}</a> [${link.getDomain()}]</div>
</#list> 

<#if error??>
<h2>Error</h2>
${error}
</#if>
</body>