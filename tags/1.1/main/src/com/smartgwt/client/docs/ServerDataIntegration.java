
package com.smartgwt.client.docs;

/**
 * Server Data Integration means:&#010 <ul>&#010 <li> You {@link com.smartgwt.client.docs.IscInstall 'install'} the {@link com.smartgwt.client.docs.IscServer 'SmartGWT Java&#010 Server'} into any J2SE/J2EE environment&#010 <li> You {@link com.smartgwt.client.docs.DataSourceDeclaration 'create DataSources'} in either XML or JavaScript,&#010 possibly on-the-fly from {@link com.smartgwt.client.docs.MetadataImport 'existing metadata'}.  &#010 <li> When you bind {@link com.smartgwt.client.widgets.DataBoundComponent} to these&#010 DataSources, the {@link com.smartgwt.client.data.DSRequest} issued by these components will be&#010 transmitted to the server using a proprietary HTTP-based protocol, and the DataSource&#010 responses likewise sent back via a proprietary protocol&#010 <li> You will use SmartGWT server APIs to receive the request data as Java Objects, and&#010 you will provide response data as Java Objects&#010 </ul>&#010 This approach is in contrast to &#010 {@link com.smartgwt.client.docs.ClientDataIntegration 'Client-side Data Integration'}, which does not require the&#010 SmartGWT server, and in which client-side DataSources are configured to directly send and&#010 receive HTTP messages containing XML, JSON or other content.&#010 <P>&#010 <B>Handling DataSource Requests</B>&#010 <P>&#010 Client-side {@link com.smartgwt.client.widgets.DataBoundComponent} will send&#010 {@link com.smartgwt.client.data.DSRequest} to the ISC server as background communications transparent to&#010 the user.  Integrating SmartGWT's DataSource layer with your data model is a matter of&#010 handling these DSRequests and sending back DSResponses, in order to fulfill the 4 basic&#010 operations of the {@link com.smartgwt.client.docs.DataSourceOperations 'DataSource Protocol'}.&#010 <P>&#010 There are two approaches for routing inbound dsRequests to your business logic:&#010 <dl>&#010 <dt>RPCManager dispatch</dt>&#010 <dd>inbound requests are handled by a single dispatcher implemented as a Java servlet or&#010 .jsp.  The {@link com.smartgwt.client.rpc.RPCManager} is used to retrieve requests and provide responses</dd>&#010 <dt>{@link com.smartgwt.client..DMI}</dt>&#010 <dd>XML declarations route requests to existing business logic methods.  Inbound request&#010 data is adapted to method parameters, and method return values are delivered as&#010 responses</dd>&#010 </dl>&#010 <P>&#010 Which approach you use is largely a matter of preference.  Direct Method Invocation (DMI)&#010 may allow simple integration without writing any SmartGWT-specific server code.&#010 RPCManager dispatch integration provides an earlier point of control, allowing logic that&#010 applies across different DataSource operations to be shared more easily.&#010 <P>&#010 Whether using RPCManager dispatch or DMI request routing, you must return data which, &#010 translated to JavaScript via the rules described in com.isomorphic.js.JSTranslater.toJS(),&#010 matches the {@link com.smartgwt.client.docs.DataSourceOperations 'response data required for the operationType'}.&#010 <P>&#010 For example, for a "fetch" request, your return data should translate to an Array of&#010 JavaScript objects.  Your backend may be capable of returning data in a number of ways - you&#010 should compare each format you can readily retrieve against the capabilities of the&#010 JSTranslater.  Common options are to pass an XML document fragment or a Collection of Java&#010 Beans/POJOs directly to DSResponse.setData().&#010 <p>&#010 For "update" and "add" DataSource requests, the inbound data is intended to be permanently&#010 stored.  If you are using a DataSource specified in XML format, you can run the validators&#010 you declared in the DataSource by calling the DSRequest.validate() method.  Assuming the&#010 declared validation is passed, you can run custom validation logic, if any, and finally&#010 create or update objects in your object model.  If you are using Beans/POJOs, the method&#010 DataSource.applyProperties(map, bean) is an easy way to apply the validated values to an&#010 Object tree or XML structure.&#010 <P>&#010 For more information on the DMI subsystem, see the {@link com.smartgwt.client..DMI} class and the &#010 <a href='/examples/server_integration/#customDataSourceIntegrationDMI' onclick="window.open('/examples/server_integration/#customDataSourceIntegrationDMI');return false;">DMI example</a> in&#010 the SDK.&#010 <P>&#010 Note that, as you continue to integrate your prototype with your backend, you can use a&#010 mixture of DataSources that have been fully integrated with your backend and DataSources&#010 that are either running in "client-only" mode (see {@link com.smartgwt.client.docs.ClientOnlyDataSources}) or&#010 that use ISC's built-in SQL connectivity (see {@link com.smartgwt.client.docs.SqlDataSource}).&#010 <P>&#010 <b>RPCManager dispatch</b>&#010 <P>&#010 The basic flow of logic for handling DataSource requests using RPCManager dispatch is:&#010 <P>&#010 <table class="normal" border=1>&#010 <tr>&#010 <td>1. Get current list of requests from the client.</td>&#010 <td>rpcManager.getRequests()</td>&#010 </tr>&#010&#010 <tr>&#010 <td>2. Determine operation type (Fetch, Add, Update, Remove) for a single request.</td>&#010 <td>dsRequest.getOperationType()</td>&#010 </tr>&#010&#010 <tr>&#010 <td>3. Get inbound values (Add, Update) and/or criteria (Fetch, Update, Remove) for this&#010 request.</td>&#010 <td>dsRequest.getFieldValue()<br>&#010 dsRequest.getValues()<br> &#010 dsRequest.getCriteria()</td>&#010 </tr>&#010&#010 <tr>&#010 <td>4. Business logic, validation, calls to data and service tiers... anything you can code.&#010 </td>&#010 <td><b>execute custom logic</b></td>&#010 </tr>&#010&#010 <tr>&#010 <td>5. Set status and data for the response.</td>&#010 <td>dsResponse.setStatus()<br>&#010 dsResponse.setData()</td>&#010 </tr>&#010&#010 <tr>&#010 <td>6. Send response to the client.</td>&#010 <td>rpcManager.send()</td>&#010 </tr>&#010 </table>&#010 <P>&#010 For more information, see the {@link com.smartgwt.client.rpc.RPCManager}, and the &#010 <a href='/examples/server_integration/#customDataSourceIntegration' onclick="window.open('/examples/server_integration/#customDataSourceIntegration');return false;">RPCManager example</a>.
 * @see com.smartgwt.client.data.DataSource#getDataFormat
 * @see com.smartgwt.client.data.DataSource#getDataProtocol
 * @see com.smartgwt.client.data.DataSource#getRequestProperties
 * @see com.smartgwt.client.data.DataSource#getServerType
 * @see com.smartgwt.client.data.DataSource#getTableName
 * @see com.smartgwt.client.data.DataSource#getDbName
 * @see com.smartgwt.client.data.DataSource#getServerObject
 * @see com.smartgwt.client.data.OperationBinding#getRequestProperties
 * @see com.smartgwt.client.types.DSDataFormat
 * @see com.smartgwt.client.types.DSServerType
 */
public interface ServerDataIntegration {
}
