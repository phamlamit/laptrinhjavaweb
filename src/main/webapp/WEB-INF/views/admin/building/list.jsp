<%@include file="/common/taglib.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<c:url var="buildingListUrl" value="/admin/building-list"/>
<c:url var="assignmentBuildingUrl" value="/api/building/assingment-building"/>
<c:url var="assignmentBuildingUpdateUrl" value="/api/building/assignemt-building/update"/>
<c:url var="buildingDeleteUrl" value="/api/buildings/delete"/>
<html>
<head>
    <title>Danh sach toa nha</title>
</head>
<body>
<div class="main-content">

    <div class="main-content-inner">
        <div class="breadcrumbs" id="breadcrumbs">
            <script type="text/javascript">
                try {
                    ace.settings.check('breadcrumbs', 'fixed')
                } catch (e) {
                }
            </script>

            <ul class="breadcrumb">
                <li>
                    <i class="ace-icon fa fa-home home-icon"></i>
                    <a href="#">Home</a>
                </li>
                <li class="active">Dashboard</li>
            </ul><!-- /.breadcrumb -->


        </div>

        <div class="page-content">

            <div class="row">
                <div class="col-xs-12">
                    <div class="widget-box">
                        <div class="widget-header">
                            <h4 class="widget-title">Tìm Kiếm</h4>

                            <div class="widget-toolbar">
                                <a href="#" data-action="collapse">
                                    <i class="ace-icon fa fa-chevron-up"></i>
                                </a>

                                <a href="#" data-action="close">
                                    <i class="ace-icon fa fa-times"></i>
                                </a>
                            </div>
                        </div>
                        <div class="widget-body">
                            <div class="widget-main">
                                <form:form commandName="modelSearch" action="${buildingListUrl}" id="listForm"
                                           method="GET">

                                    <div class="row">
                                        <div class="col-xs-12">
                                            <!-- PAGE CONTENT BEGINS -->

                                            <div class="col-sm-6">
                                                <div>
                                                    <label for="name">Tên tòa nhà</label>
                                                    <form:input path="name" cssClass="form-control"/>
                                                </div>
                                            </div>

                                            <div class="col-sm-6">
                                                <div>
                                                    <label for="floorArea">Diện tích sàn</label>
                                                    <input type="number" id="floorArea" name="floorArea"
                                                           value="${modelSearch.floorArea}" class="form-control">

                                                </div>
                                            </div>
                                            <br>
                                            <div class="col-sm-4">
                                                <div class="">
                                                    <label for="">Quận hiện có</label>

                                                    <form:select path="district" id="district">
                                                        <form:option value="" label="---Chọn Quận--"/>
                                                        <form:options items="${districts}"/>
                                                    </form:select>
                                                </div>
                                            </div>
                                            <div class="col-sm-4">
                                                <div>
                                                    <label for="ward">Phường</label>
                                                    <input type="text" id="ward" for="ward" name="ward" value="${modelSearch.ward}"
                                                           class="form-control">
                                                </div>
                                            </div>
                                            <div class="col-sm-4">
                                                <div>
                                                    <label for="street">Đường</label>
                                                    <input type="text" id="street" for="street" name="street" value="${modelSearch.street}"
                                                           class="form-control">
                                                </div>
                                            </div>


                                            <div class="col-sm-4">
                                                <div>
                                                    <label for="numberOfBasement">Số tàng hầm</label>
                                                    <input type="number" id="numberOfBasement" name="numberOfBasement" value="${modelSearch.numberOfBasement}"
                                                           class="form-control">
                                                </div>
                                            </div>
                                            <div class="col-sm-4">
                                                <div>
                                                    <label for="direction">Hướng</label>
                                                    <input type="text" id="direction" name="direction" value="${modelSearch.direction}"
                                                           class="form-control">
                                                </div>
                                            </div>
                                            <div class="col-sm-4">
                                                <div>
                                                    <label for="level">Hạng</label>
                                                    <input type="text" id="level" name="level" value="${modelSearch.level}"
                                                           class="form-control">
                                                </div>
                                            </div>
                                            <div class="col-sm-3">
                                                <div>
                                                    <label for="rentAreaFrom">Diện tích từ</label>
                                                    <input type="number" id="rentAreaFrom" name="rentAreaFrom" value="${modelSearch.rentAreaFrom}"
                                                           class="form-control">
                                                </div>
                                            </div>
                                            <div class="col-sm-3">
                                                <div>
                                                    <label for="rentAreaTo">Diện tích đến</label>
                                                    <input type="number" id="rentAreaTo" name="rentAreaTo" value="${modelSearch.rentAreaTo}"
                                                           class="form-control">
                                                </div>
                                            </div>
                                            <div class="col-sm-3">
                                                <div>
                                                    <label for="rentPriceFrom">Giá thuê từ</label>
                                                    <input type="number" id="rentPriceFrom" name="rentPriceFrom" value="${modelSearch.rentPriceFrom}"
                                                           class="form-control">
                                                </div>
                                            </div>
                                            <div class="col-sm-3">
                                                <div>
                                                    <label for="rentPriceTo">Giá thuê đến</label>
                                                    <input type="number" id="rentPriceTo" name="rentPriceTo" value="${modelSearch.rentPriceTo}"
                                                           class="form-control">
                                                </div>
                                            </div>
                                            <br>
                                            <div class="col-sm-4">
                                                <div>
                                                    <label for="">Tên Quản Lý</label>
                                                    <input type="text" name="" value=""
                                                           class="form-control">
                                                </div>
                                            </div>
                                            <div class="col-sm-4">
                                                <div>
                                                    <label for="">Điện thoại quản lý</label>
                                                    <input type="text" for=""
                                                           class="form-control">
                                                </div>
                                            </div>

                                            <div class="col-sm-4 mt-4">
                                                <div>
                                                    <label for="">Chọn Nhân viên phụ trách</label>
                                                    <form:select path="staffID">
                                                        <form:option value="-1" label="--Chon Nhan Vien Phu Trach"/>
                                                        <form:options items="${staffMaps}"/>
                                                    </form:select>
                                                </div>
                                            </div>

                                            <div class="col-sm-12 form-check">


                                                <form:checkboxes path="types" items="${mapTypes}"/>

                                            </div>
                                            <div class="row">
                                                <div class="col-sm-12">
                                                    <button type="button" id="btnSearch" class="btn btn-primary">Tìm
                                                        Kiếm
                                                    </button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                </form:form>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
            <div class="row">
                <div class="col-xs-12">
                    <div class="pull-right">
                        <button class="btn btn-white btn-info btn-bold" data-toggle="tooltip"
                                title="Thêm Tòa Nhà">
                            <i class="fa fa-plus-circle	"></i>
                        </button>
                        <button class="btn btn-white btn-danger btn-bold" data-toggle="tooltip"
                                title="Xóa Tòa Nhà">
                            <i class="fa fa-trash"></i>
                        </button>

                    </div>
                </div>

            </div>
            <br>
            <!-- PAGE CONTENT ENDS -->
            <div class="row m">
                <div class="col-xs-12">
                    <div>
                        <table id="dynamic-table" class="table table-striped table-bordered table-hover">
                            <thead>
                            <tr>
                                <th>Tên sản phẩm</th>
                                <th>Địa chỉ</th>
                                <th>Tên Quản Lý</th>
                                <th>Số điện thoại</th>
                                <th>Diện tích sàn</th>
                                <th>Giá thuê</th>
                                <th>Phí dịch vụ</th>
                                <th>Thao Tác</th>

                            </tr>
                            </thead>
                            <tbody>

                            <c:forEach var="item" items="${buildings}">
                                <tr value="${item.id}">
                                    <td><input type="checkbox" value="${item.id}" id="checkbox_${item.id}"></td>
                                    <td>${item.name}</td>
                                    <td>${item.ward}</td>
                                    <td>${item.type}</td>
                                    <td>${item.street}</td>
                                    <td>${item.street}</td>
                                    <td>${item.street}</td>

                                    <td>
                                        <div class="hidden-sm hidden-xs btn-group">

                                            <button class="btn btn-xs btn-info e_handle_assignment"
                                                    data-toggle="tooltip"

                                                    title="Giao Tòa Nhà">
                                                <i class="ace-icon fa fa-reorder bigger-120"></i>
                                            </button>
                                            <c:url var="updateNewURL" value="/admin/building-edit">
                                                <c:param name="id" value="${item.id}"/>
                                            </c:url>
                                            <a class="btn btn-xs btn-success e_handle_update" data-toggle="tooltip"
                                                    href="${updateNewURL}"
                                                    title="Sửa Tòa Nhà">
                                                <i class="ace-icon fa fa-pencil bigger-120"></i>
                                            </a>

                                            <button class="btn btn-xs btn-danger e_handle_delete" data-toggle="tooltip"
                                                    title="Xóa Tòa Nhà">
                                                <i class="ace-icon fa fa-trash-o bigger-120"></i>
                                            </button>
                                        </div>


                                    </td>
                                </tr>
                            </c:forEach>

                            </tbody>
                        </table>
                    </div>
                </div>
            </div>

        </div><!-- /.col -->


    </div><!-- /.row -->


</div><!-- /.row -->
<div id="assignmentBuildingModal" class="modal fade" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Danh sách nhân viên</h4>
            </div>
            <div class="modal-body" id="">
                <table id="dynamic-table" class="table table-striped table-bordered table-hover">
                    <thead>
                    <tr>
                        <th></th>
                        <th>Tên nhân viên</th>
                    </tr>
                    </thead>
                    <tbody id="model-body"></tbody>
                </table>

            </div>
            <div class="modal-footer">
                <button type="button" id="btnAssignmentBuilding" class="btn btn-success" data-dismiss="modal">Giao Tòa
                    Nhà
                </button>
                <button type="button" class="btn btn-primary" data-dismiss="modal">Đóng</button>
            </div>
        </div>

    </div>
</div>


<!--[if !IE]> -->
<script src="assets/js/jquery.2.1.1.min.js"></script>

<!-- <![endif]-->

<!--[if IE]>
<script src="assets/js/jquery.1.11.1.min.js"></script>
<![endif]-->

<!--[if !IE]> -->
<script type="text/javascript">
    window.jQuery || document.write("<script src='assets/js/jquery.min.js'>" + "<" + "/script>");
</script>

<!-- <![endif]-->

<!--[if IE]>
<script type="text/javascript">
    window.jQuery || document.write("<script src='assets/js/jquery1x.min.js'>" + "<" + "/script>");
</script>
<![endif]-->
<script type="text/javascript">
    if ('ontouchstart' in document.documentElement) document.write("<script src='assets/js/jquery.mobile.custom.min.js'>" + "<" + "/script>");
</script>
<script src="assets/js/bootstrap.min.js"></script>

<!-- page specific plugin scripts -->

<!--[if lte IE 8]>
<script src="assets/js/excanvas.min.js"></script>
<![endif]-->
<script src="assets/js/jquery-ui.custom.min.js"></script>
<script src="assets/js/jquery.ui.touch-punch.min.js"></script>
<script src="assets/js/jquery.easypiechart.min.js"></script>
<script src="assets/js/jquery.sparkline.min.js"></script>
<script src="assets/js/jquery.flot.min.js"></script>
<script src="assets/js/jquery.flot.pie.min.js"></script>
<script src="assets/js/jquery.flot.resize.min.js"></script>

<!-- ace scripts -->
<script src="assets/js/ace-elements.min.js"></script>
<script src="assets/js/ace.min.js"></script>

<!-- inline scripts related to this page -->
<script type="text/javascript">
    jQuery(function ($) {
        $('.easy-pie-chart.percentage').each(function () {
            var $box = $(this).closest('.infobox');
            var barColor = $(this).data('color') || (!$box.hasClass('infobox-dark') ? $box.css('color') : 'rgba(255,255,255,0.95)');
            var trackColor = barColor == 'rgba(255,255,255,0.95)' ? 'rgba(255,255,255,0.25)' : '#E2E2E2';
            var size = parseInt($(this).data('size')) || 50;
            $(this).easyPieChart({
                barColor: barColor,
                trackColor: trackColor,
                scaleColor: false,
                lineCap: 'butt',
                lineWidth: parseInt(size / 10),
                animate: /msie\s*(8|7|6)/.test(navigator.userAgent.toLowerCase()) ? false : 1000,
                size: size
            });
        })

        $('.sparkline').each(function () {
            var $box = $(this).closest('.infobox');
            var barColor = !$box.hasClass('infobox-dark') ? $box.css('color') : '#FFF';
            $(this).sparkline('html',
                {
                    tagValuesAttribute: 'data-values',
                    type: 'bar',
                    barColor: barColor,
                    chartRangeMin: $(this).data('min') || 0
                });
        });

        $(document).on('click', '.e_handle_assignment', assignmentBuilding);
        $(document).on('click', '.e_handle_update', updateBuilding);
        $(document).on('click', '.e_handle_delete', deleteBuilding);


        //flot chart resize plugin, somehow manipulates default browser resize event to optimize it!
        //but sometimes it brings up errors with normal resize event handlers
        $.resize.throttleWindow = false;

        var placeholder = $('#piechart-placeholder').css({'width': '90%', 'min-height': '150px'});
        var data = [
            {label: "social networks", data: 38.7, color: "#68BC31"},
            {label: "search engines", data: 24.5, color: "#2091CF"},
            {label: "ad campaigns", data: 8.2, color: "#AF4E96"},
            {label: "direct traffic", data: 18.6, color: "#DA5430"},
            {label: "other", data: 10, color: "#FEE074"}
        ]

        function drawPieChart(placeholder, data, position) {
            $.plot(placeholder, data, {
                series: {
                    pie: {
                        show: true,
                        tilt: 0.8,
                        highlight: {
                            opacity: 0.25
                        },
                        stroke: {
                            color: '#fff',
                            width: 2
                        },
                        startAngle: 2
                    }
                },
                legend: {
                    show: true,
                    position: position || "ne",
                    labelBoxBorderColor: null,
                    margin: [-30, 15]
                }
                ,
                grid: {
                    hoverable: true,
                    clickable: true
                }
            })
        }

        drawPieChart(placeholder, data);

        /**
         we saved the drawing function and the data to redraw with different position later when switching to RTL mode dynamically
         so that's not needed actually.
         */
        placeholder.data('chart', data);
        placeholder.data('draw', drawPieChart);


        //pie chart tooltip example
        var $tooltip = $("<div class='tooltip top in'><div class='tooltip-inner'></div></div>").hide().appendTo('body');
        var previousPoint = null;

        placeholder.on('plothover', function (event, pos, item) {
            if (item) {
                if (previousPoint != item.seriesIndex) {
                    previousPoint = item.seriesIndex;
                    var tip = item.series['label'] + " : " + item.series['percent'] + '%';
                    $tooltip.show().children(0).text(tip);
                }
                $tooltip.css({top: pos.pageY + 10, left: pos.pageX + 10});
            } else {
                $tooltip.hide();
                previousPoint = null;
            }

        });

        /////////////////////////////////////
        $(document).one('ajaxloadstart.page', function (e) {
            $tooltip.remove();
        });


        var d1 = [];
        for (var i = 0; i < Math.PI * 2; i += 0.5) {
            d1.push([i, Math.sin(i)]);
        }

        var d2 = [];
        for (var i = 0; i < Math.PI * 2; i += 0.5) {
            d2.push([i, Math.cos(i)]);
        }

        var d3 = [];
        for (var i = 0; i < Math.PI * 2; i += 0.2) {
            d3.push([i, Math.tan(i)]);
        }


        var sales_charts = $('#sales-charts').css({'width': '100%', 'height': '220px'});
        $.plot("#sales-charts", [
            {label: "Domains", data: d1},
            {label: "Hosting", data: d2},
            {label: "Services", data: d3}
        ], {
            hoverable: true,
            shadowSize: 0,
            series: {
                lines: {show: true},
                points: {show: true}
            },
            xaxis: {
                tickLength: 0
            },
            yaxis: {
                ticks: 10,
                min: -2,
                max: 2,
                tickDecimals: 3
            },
            grid: {
                backgroundColor: {colors: ["#fff", "#fff"]},
                borderWidth: 1,
                borderColor: '#555'
            }
        });


        $('#recent-box [data-rel="tooltip"]').tooltip({placement: tooltip_placement});

        function tooltip_placement(context, source) {
            var $source = $(source);
            var $parent = $source.closest('.tab-content')
            var off1 = $parent.offset();
            var w1 = $parent.width();

            var off2 = $source.offset();
            //var w2 = $source.width();

            if (parseInt(off2.left) < parseInt(off1.left) + parseInt(w1 / 2)) return 'right';
            return 'left';
        }


        $('.dialogs,.comments').ace_scroll({
            size: 300
        });


        //Android's default browser somehow is confused when tapping on label which will lead to dragging the task
        //so disable dragging when clicking on label
        var agent = navigator.userAgent.toLowerCase();
        if ("ontouchstart" in document && /applewebkit/.test(agent) && /android/.test(agent))
            $('#tasks').on('touchstart', function (e) {
                var li = $(e.target).closest('#tasks li');
                if (li.length == 0) return;
                var label = li.find('label.inline').get(0);
                if (label == e.target || $.contains(label, e.target)) e.stopImmediatePropagation();
            });

        $('#tasks').sortable({
                opacity: 0.8,
                revert: true,
                forceHelperSize: true,
                placeholder: 'draggable-placeholder',
                forcePlaceholderSize: true,
                tolerance: 'pointer',
                stop: function (event, ui) {
                    //just for Chrome!!!! so that dropdowns on items don't appear below other items after being moved
                    $(ui.item).css('z-index', 'auto');
                }
            }
        );
        $('#tasks').disableSelection();
        $('#tasks input:checkbox').removeAttr('checked').on('click', function () {
            if (this.checked) $(this).closest('li').addClass('selected');
            else $(this).closest('li').removeClass('selected');
        });


        //show the dropdowns on top or bottom depending on window height and menu position
        $('#task-tab .dropdown-hover').on('mouseenter', function (e) {
            var offset = $(this).offset();

            var $w = $(window)
            if (offset.top > $w.scrollTop() + $w.innerHeight() - 100)
                $(this).addClass('dropup');
            else $(this).removeClass('dropup');
        });

    })
    $('#btnSearch').click(function (e) {
        e.preventDefault();
        $('#listForm').submit();
    });

    function assignmentBuilding(e) {
        var id = $(this).closest('tr').attr('value');
        assignmentBuildingModal();
        getAssignmentBuilding(id);
    }

    function assignmentBuildingModal() {
        $('#model-body').empty();
        $('#assignmentBuildingModal').modal();
    }

    function getAssignmentBuilding(id) {
        $.ajax({
            url: '${assignmentBuildingUrl}',
            type: "GET",
            dataType: 'json',
            contentType: "application/json",
            data: {
                'id': id
            },
            success: function (reponse) {
                console.log(reponse);
                $('#btnAssignmentBuilding').attr('id', id);
                var users = reponse;
                users.forEach(user => {
                    console.log("user " + user.id);
                    if (user.checked == true) {
                        var row = "<tr><td><input type='checkbox' checked value='" + user.id + "' id='checkbox_" + user.id + "'></td><td>" + user.fullName + "</td></tr>"
                        $('#model-body').append(row);
                    } else {
                        var row = "<tr><td><input type='checkbox' value='" + user.id + "' id='checkbox_" + user.id + "'></td><td>" + user.fullName + "</td></tr>"
                        $('#model-body').append(row);
                    }

                });
            },
            error: function (response) {
                console.log("failed" + response);
            },
        });
    }

    $('#btnAssignmentBuilding').click(function (e) {
        var data = [];
        var a = $("input:checked").val();
        for (i = 0; i < $("input:checked").length; i++) {
            a = $("input:checked")[i];
            data.push($(a).val());
        }
        var id = $(this).attr('id');
        console.log(id);
        console.log('sucess');
        var assignmentBuilding = {
            buildingId: id,
            staffId: data,

        }
        $.ajax({
            url: '${assignmentBuildingUpdateUrl}',
            type: "POST",
            dataType: "json",
            contentType: "application/json",
            data: JSON.stringify(assignmentBuilding),
            sucess: function (reponse) {
                console.log("sucess") + reponse
            },
            error: function (reponse) {
                console.log("error") + reponse
            }
        });
    })

    function deleteBuilding() {
        var id = $(this).closest('tr').attr('value');
        $.ajax({
            url: '${buildingDeleteUrl}',
            type: "GET",
            dataType: 'json',
            contentType: "application/json",
            data: {
                'id': id
            },
            sucess: function (reponse) {
                console.log(reponse);
                location.reload();
            },
            error: function (reponse) {
                console.log(reponse);
                location.reload();
            }
        })
    }

    function updateBuilding() {
        var id = $(this).closest('tr').attr('value');
        $(window).attr('location', '/admin/building-edit/' + id);
    }
</script>


</body>
</html>
