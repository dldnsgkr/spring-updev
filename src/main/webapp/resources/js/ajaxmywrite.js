function del()
{
	var b_num = $("#b_num").val();
	var b_kind = $("#b_kind").val();
  var x = confirm("이 게시물을 정말 삭제 하시겠습니까?");
  if (x)
      location.href="writedelete?b_num="+b_num+"&b_kind="+b_kind;
  else
    return false;
}