<!DOCTYPE html>
<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--> <html lang="en" class="no-js"><!--<![endif]-->
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title><g:layoutTitle default="Grails"/></title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="shortcut icon" href="${assetPath(src: 'favicon.ico')}" type="image/x-icon">
    <link rel="apple-touch-icon" href="${assetPath(src: 'apple-touch-icon.png')}">
    <link rel="apple-touch-icon" sizes="114x114" href="${assetPath(src: 'apple-touch-icon-retina.png')}">
    <asset:stylesheet src="application.css"/>
    <asset:javascript src="application.js"/>
    <g:layoutHead/>
    <g:javascript>
        function Save(val) {
            $.ajax({
                url: "http://localhost:8080/TekDays/lang/getLang",
                type: "POST",
                data: {
                    lang: val.value
                },
                success: function (response) {
                    window.location.href = response
                },
                error: function (request, status, error) {
                    alert(request.responseText);
                }
            });
        }
    </g:javascript>
</head>

<body>
<div id="logo" role="banner">
    <img src="${resource(dir: 'images', file: 'pic.jpg')}" alt="TekDays" style="width:-webkit-fill-available"/>
    <g:loginToggle/> <g:register/>

    <br>
</div>

<div style="float:right">
    <select class="form-control" onChange="Save(this)">
        <option value="">Language</option>
        <option value="en">English🇺🇲</option>
        <option value="hy">Հայերեն🇦🇲</option>
        <option value="ru">Русский🇷🇺</option>
    </select>
</div>

<g:layoutBody/>
<div class="footer" role="contentinfo"></div>

<div id="spinner" class="spinner" style="display:none;"><g:message code="spinner.alt" default="Loading&hellip;"/></div>

</body>
</html>



