<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Documents</title>
</head>

<body>
<h1>Document List</h1>

<hr/>


<div id="my-inbox">
    <h2>Action Inbox</h2>
    <table>
        <tr>
            <th>Module Code</th>
            <th>Title</th>
            <th>Proposer</th>
            <th>Next Action</th>
        </tr>
        <g:each in="${actionInbox}">
            <tr>
                <td><g:link controller="document" action="${it.action}" params="[documentId:it.proposalId]">${it.proposalId}</g:link></td>
                <td>${it.title}</td>
                <td>${it.proposer}</td>
                <td>${it.yourAction}</td>
                <td>${it.moduleCode}</td>
            </tr>
        </g:each>
    </table>
</div>

<hr/>
<g:link elementId="new-doc" controller="document" action="propose">Propose a new Document</g:link><br/>

</body>
</html>
