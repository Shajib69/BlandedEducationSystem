<%@page import="java.util.Date"%>
<%@include file="header.jsp" %>
<div id="page-wrapper" style="min-height: 800px;">
    <div class="container-fluid">
        <!-- Page Heading -->
        <div class="row">
            <div class="col-lg-12">
                <h2 class="page-header">
                    <i class="fa fa-calendar"></i> Create Event
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
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4>Create Event</h4>
                    </div>
                    <div class="panel-body">
                        <form action="${pageContext.request.contextPath}/admin/saveEvent" method="post">
                            
                            
                            <div class="form-group">
                                <label class="control-label">Event Name</label>
                                <input name="eventName" type="text" class="form-control" placeholder="Enter Event Name"/>
                            </div>
                            <div class="form-group">
                                <label class="control-label">Creator Name</label>
                                <input type="text" name="eventCreatorName" class="form-control"/>
                            </div>
                            <div class="form-group">
                                <label class="control-label">Event Date (dd-MM-yyyy hh:mm:ss)</label>
                                <input placeholder="Enter date in this format (dd-MM-yyyy hh:mm:ss)" pattern="/([0-2][0-9]{3})\-([0-1][0-9])\-([0-3][0-9])T([0-5][0-9])\:([0-5][0-9])\:([0-5][0-9])(Z|([\-\+]([0-1][0-9])\:00))/"  type="datetime" name="eventDate" class="form-control"/>
                                
                            </div>
                            <div class="form-group">
                                <label class="control-label">Description</label>
                                <textarea name="description" class="form-control"></textarea>
                            </div>
                            <div class="form-group">
                                <label class="control-label"><i class="fa fa-dot-circle-o"></i> Live Video Url</label>
                                <textarea name="videoUrl" class="form-control"></textarea>
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