<%@page import="java.util.Date"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@include file="header.jsp" %>
<div id="page-wrapper" style="min-height: 800px;">
    <div class="container-fluid">
        <!-- Page Heading -->
        <div class="row">
            <div class="col-lg-12">
                <h2 class="page-header">
                    <i class="fa fa-user-plus"></i> Add User
                </h2>
            </div>
        </div>

        <div class="row">
            <div class="col-md-12">
                <c:if test="${sm != null}">
                    <div class="alert alert-success alert-dismissable">
                        <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                        <strong>Success!</strong> ${sm}
                    </div>
                </c:if>
                <c:if test="${em != null}">
                    <div class="alert alert-danger alert-dismissable">
                        <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                        <strong>Error!</strong> ${em}
                    </div>
                </c:if>
            </div>
        </div>

        <div class="row">
            <div class="col-md-4">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4>Create User Role</h4>
                    </div>
                    <div class="panel-body">
                        <form action="${pageContext.request.contextPath}/admin/saveUserRole" method="post">
                            <div class="form-group">
                                <label class="control-label" for="roleName">Role Name:</label>
                                <input  name="roleName" type="text" class="form-control" id="roleName" placeholder="Enter Role Name"/>
                            </div>
                            <div class="form-group"> 
                                <button type="submit" class="btn btn-success">Create</button>
                                <button type="reset" class="btn btn-default">Reset</button>
                            </div>
                        </form>
                    </div>
                    <div class="panel-footer"></div>
                </div>
            </div>

<!--            <div class="col-md-8 pull-right">
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <h4>Create User</h4>
                    </div>
                    <div class="panel-body">
                        <form action="${pageContext.request.contextPath}/admin/saveUser" method="post" commandName="users">
                            <div class="form-group">
                                <label class="control-label" for="username">User Name:</label>
                                <input name="username" type="text" class="form-control" id="username" placeholder="Enter User Name"/>
                            </div>
                            <div class="form-group">
                                <label class="control-label" for="email">Email:</label>
                                <input name="email" type="email" class="form-control" id="email" placeholder="Enter Email">
                            </div>
                            <div class="form-group">
                                <label class="control-label" for="pwd">Password:</label>
                                <input name="password" type="password" class="form-control" id="pwd" placeholder="Enter password">
                            </div>
                            <div class="form-group">
                                <label class="control-label" for="role">Role: </label>
                               
                                <select name="authority" class="form-control">
                                    <option value="0">--- Select Role ---</option>
                                    <c:forEach var="row" items="${userRoles}">
                                        <option value="${row.roleName}">${row.roleName}</option>
                                    </c:forEach>
                                </select>
                                
                            </div>
                            <div class="form-group">
                                <label class="control-label">Register Date: </label>
                                <input pattern="dd-MM-yyyy" type="date" name="registerDate" class="form-control"/>
                            </div>
                            
                            <div class="form-group"> 
                                <div class="checkbox">
                                    <label><input type="checkbox" name="enabled"> Active</label>
                                </div>
                            </div>
                            <div class="form-group"> 
                                <button type="submit" class="btn btn-success">Create</button>
                                <button type="reset" class="btn btn-default">Reset</button>
                            </div>
                        </form>
                    </div>
                    <div class="panel-footer"></div>
                </div>
            </div>-->



        </div>





    </div>
</div>

<%@include file="footer.jsp" %>