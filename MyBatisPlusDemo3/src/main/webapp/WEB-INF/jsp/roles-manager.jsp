<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!-- 角色列表 -->
<div class="easyui-layout" fit="true" border="false">
	<!-- 角色菜单 -->
	<div data-options="region:'west',split:true,width:130,border:false">
		<ul title="角色" id="roles_manager_menus"></ul>
	</div>

	<!-- 该角色下的用户 -->
	<div data-options="region:'center',fit:true,border:false" style="overflow: hidden">
		
	</div>
</div>
<div id="roles_manager_menus_toolbar" class="easyui-menu"
	style="width: 120px;" data-options="onClick:menuHandler">
	<div data-options="iconCls:'icon-add',name:'add'">添加</div>
	<div data-options="iconCls:'icon-remove',name:'rename'">重命名</div>
	<div class="menu-sep"></div>
	<div data-options="iconCls:'icon-remove',name:'delete'">删除</div>
</div>

<script type="text/javascript" src="../js/common/commonutil.js"></script>
<script type="text/javascript">
	var rolesManagerMenus = $("#roles_manager_menus");
	var rolesManagerMenusToolbar = $("#roles_manager_menus_toolbar");
	$(function() {
		rolesManagerMenus.tree({
			url : '/roles/rolesList',
			animate : true,
			method : "GET",
			
			//右击鼠标触发
			onContextMenu : function(e, node) {
				//关闭鼠标原来的点击事件
				e.preventDefault();
				//选中鼠标点击的节点
				$(this).tree('select', node.target);
				//展示菜单栏，并将其显示在鼠标位置附近
				rolesManagerMenusToolbar.menu('show', {
					//鼠标的位置
					left : e.pageX,
					top : e.pageY
				});
			},
			
			//在新添加的节点被编辑之后触发
			onAfterEdit : function(node) {
				//获取树本身
				var _tree = $(this);
				//判断选择的节点id是否等于0
				if (node.id == 0) {
					// 新增节点
					$.post("/roles/rolesAdd", {
						rolesParent : node.parentId,
						rolesName : node.text
					}, function(data) {
						console.info(data);
						
						if (data.success) {
							//更新节点
							_tree.tree("update", {
								target : node.target,
								id : data.rolesId
							});
						} else {
							$.messager.alert('提示', '创建' + node.text + ' 分类失败!');
						}
					});
				} else {
					$.post("/roles/rolesUpdate", {
						rolesId : node.id,
						rolesName : node.text
					});
				}
			}
		});
	});

	//处理点击菜单栏的事件
	function menuHandler(item) {
		var node = rolesManagerMenus.tree("getSelected");

		//判断点击的是那个
		if (item.name === "add") {
			rolesManagerMenus.tree('append', {
				parent : (node ? node.target : null),
				data : [ {
					text : '新建分类',//新建节点的名称
					id : 0,//新建节点的id
					parentId : node.id//新建节点的父节点的id
				} ]
			});
			//选中id为0的节点，即新增的节点
			var _node = rolesManagerMenus.tree('find', 0);
			//选中id为0的节点，即新增的节点，开启编辑
			rolesManagerMenus.tree("select", _node.target).tree('beginEdit', _node.target);
		} else if (item.name === "rename") {
			rolesManagerMenus.tree('beginEdit', node.target);
		} else if (item.name === "delete") {
			$.messager.confirm('确认', '确定删除名为 ' + node.text + ' 的分类吗？',function(r) {
				if (r) {
					$.post("/roles/removeRoles", {
						rolesId : node.id
					}, function() {
						rolesManagerMenus.tree("remove", node.target);
					});
				}
			});
		}
	}
</script>















