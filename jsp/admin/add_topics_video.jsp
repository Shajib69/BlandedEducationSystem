<%@page import="java.util.Date"%>
<%@include file="header.jsp" %>
<div id="page-wrapper" style="min-height: 800px;">
    <div class="container-fluid">
        <!-- Page Heading -->
        <div class="row">
            <div class="col-lg-12">
                <h2 class="page-header">
                    <i class="fa fa-video-camera"></i> Topics Video
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
                        <h4>Add Topics Video Url</h4>
                    </div>
                    <div class="panel-body">

                        <form action="${pageContext.request.contextPath}/admin/updateTopicsVideoUrl" method="post">
                            <div class="form-group">
                                <label class="control-label">Topics Title:</label>
                                <input value="${topic.topicsId}"  name="topicsId" type="hidden" class="form-control">
                                <input value="${topic.topicsTitle}" readonly=""  name="topicsTitle" type="text" class="form-control">
                            </div>
                            <div class="form-group">
                                <label class="control-label">Video URL</label>
                                <textarea name="videoUrl" class="form-control">${topic.videoUrl}</textarea>
                            </div>
                            
                            <div class="form-group"> 
                                <button type="submit" class="btn btn-success">Update</button>
                                <button type="reset" class="btn btn-default">Reset</button>
                                <a href="${pageContext.request.contextPath}/admin/list_topics" class="btn btn-warning pull-right">
                                    <i class="fa fa-backward"></i> Back to list
                                </a>
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