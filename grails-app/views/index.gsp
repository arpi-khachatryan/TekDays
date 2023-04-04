<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>TekDays - The Community is the Conference!</title>
</head>

<body>
<div id="welcome" style='margin-left:25px; margin-top:25px; width:85%'>
    <br/>

    <h3>${message(code: "welcome", default: "Welcome to")} TekDays.com</h3>

    <p>TekDays.com is a site dedicated to assisting individuals and
    communities to organize technology conferences. To bring great
    minds with common interests and passions together for the good
    of greater geekdom!</p>
</div>
<br>

<g:organizerEvents/>
<br>

<g:volunteerEvents/>
<br>

<div class="homeCell" style='margin-left:25px; margin-top:25px; width:85%'>
    <h3>${message(code: "findAnEvent", default: "Find an Event")}</h3>
    <p>See if there's a technical event in the works that strikes your
    fancy. If there is, you can volunteer to help or just let the
    organizers know that you'd be interested in attending.
    Everybody has a role to play.</p>
    <br>

    <span class="buttons" style="margin-left: auto; margin-right: auto;">
        <g:link controller="tekEvent" action="index">Find a Tek Event</g:link>
    </span>
</div>
<br>

<div class="homeCell" style='margin-left:25px; margin-top:25px; width:85%'>
    <h3>${message(code: "organizeEvent", default: "Organize an Event")}</h3>

    <p>If you don't see anything that suits your interest and location,
    then why not get the ball rolling. It's easy to get started and
    there may be others out there ready to get behind you to make it
    happen.</p>
    <br>

    <span class="buttons" style="margin-left: auto; margin-right: auto;">
        <g:link controller="tekEvent" action="create">Organize a Tek Event</g:link>
    </span>
</div>
<br>

<div class="homeCell" style='margin-left:25px; margin-top:25px; width:85%'>
    <h3>${message(code: "sponsorEvent", default: "Sponsor a Tek Event")}</h3>

    <p>If you are part of a business or organization that is involved in
    technology then sponsoring a tek event would be a great way to
    let the community know that you're there and you're involved.</p>
    <br>

    <span class="buttons" style="margin-left: auto; margin-right: auto;">
        <g:link controller="sponsor" action="create">Sponsor a Tek Event</g:link>
    </span>
</div>
</body>

%{--	<body>--}%
%{--		<a href="#page-body" class="skip"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>--}%
%{--		<div id="status" role="complementary">--}%
%{--			<h1>Application Status</h1>--}%
%{--			<ul>--}%
%{--				<li>App version: <g:meta name="app.version"/></li>--}%
%{--				<li>Grails version: <g:meta name="app.grails.version"/></li>--}%
%{--				<li>Groovy version: ${GroovySystem.getVersion()}</li>--}%
%{--				<li>JVM version: ${System.getProperty('java.version')}</li>--}%
%{--				<li>Reloading active: ${grails.util.Environment.reloadingAgentEnabled}</li>--}%
%{--				<li>Controllers: ${grailsApplication.controllerClasses.size()}</li>--}%
%{--				<li>Domains: ${grailsApplication.domainClasses.size()}</li>--}%
%{--				<li>Services: ${grailsApplication.serviceClasses.size()}</li>--}%
%{--				<li>Tag Libraries: ${grailsApplication.tagLibClasses.size()}</li>--}%
%{--			</ul>--}%
%{--			<h1>Installed Plugins</h1>--}%
%{--			<ul>--}%
%{--				<g:each var="plugin" in="${applicationContext.getBean('pluginManager').allPlugins}">--}%
%{--					<li>${plugin.name} - ${plugin.version}</li>--}%
%{--				</g:each>--}%
%{--			</ul>--}%
%{--		</div>--}%
%{--		<div id="page-body" role="main">--}%
%{--			<h1>Welcome to Grails</h1>--}%
%{--			<p>Congratulations, you have successfully started your first Grails application! At the moment--}%
%{--			   this is the default page, feel free to modify it to either redirect to a controller or display whatever--}%
%{--			   content you may choose. Below is a list of controllers that are currently deployed in this application,--}%
%{--			   click on each to execute its default action:</p>--}%

%{--			<div id="controller-list" role="navigation">--}%
%{--				<h2>Available Controllers:</h2>--}%
%{--				<ul>--}%
%{--					<g:each var="c" in="${grailsApplication.controllerClasses.sort { it.fullName } }">--}%
%{--						<li class="controller"><g:link controller="${c.logicalPropertyName}">${c.fullName}</g:link></li>--}%
%{--					</g:each>--}%
%{--				</ul>--}%
%{--			</div>--}%
%{--		</div>--}%
%{--	</body>--}%
