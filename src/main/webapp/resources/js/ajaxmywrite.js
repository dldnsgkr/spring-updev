function del()
{
	var b_num = $("#b_num").val();
	var b_kind = $("#b_kind").val();
  var x = confirm("Are you sure you want to delete?");
  if (x)
      location.href="writedelete?b_num="+b_num+"&b_kind="+b_kind;
  else
    return false;
}