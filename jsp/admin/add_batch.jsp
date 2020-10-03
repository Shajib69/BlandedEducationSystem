<%@page import="java.util.Date"%>
<%@include file="header.jsp" %>
<div id="page-wrapper" style="min-height: 800px;">
    <div class="container-fluid">
        <!-- Page Heading -->
        <div class="row">
            <div class="col-lg-12">
                <h2 class="page-header">
                    <i class="fa fa-file"></i> Batch
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

        <div class="row" style="margin-top: 20px;">
            <div class="col-md-12">
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <h4>Create Batch</h4>
                    </div>
                    <div class="panel-body">
                        <form action="${pageContext.request.contextPath}/admin/saveBatch" method="post">


                            <div class="form-group">
                                <label class="control-label">Batch Name:</label>
                                <input  name="batchName" type="text" class="form-control" id="batchName" placeholder="Enter Bathch Name"/>
                            </div>
                            <div class="form-group">
                                <label class="control-label">Session</label>
                                <input type="text" name="session" class="form-control"/>
                            </div>
                            <div class="form-group">
                                <label class="control-label">Start Date</label>
                                <input type="date" name="startDate" class="form-control"/>
                            </div>
                            <div class="form-group">
                                <label class="control-label">End Date</label>
                                <input type="date" name="endDate" class="form-control"/>
                            </div>

                            <div class="form-group"> 
                                <div class="checkbox">
                                    <label><input type="checkbox" name="status"> Active</label>
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
            </div>
        </div>





    </div>
</div>

<%@include file="footer.jsp" %>