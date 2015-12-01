SET serveroutput ON;
DECLARE
  v_result VARCHAR2(32767) := '';
  CURSOR v_cur IS SELECT ENAME FROM scott.emp;
  v_ename VARCHAR2(50);
BEGIN
  OPEN v_cur;
  LOOP
    FETCH v_cur INTO v_ename;
    EXIT WHEN v_cur%NOTFOUND;
    v_result := v_result||v_ename||'--';
  END LOOP;
  dbms_output.put_line(rtrim(v_result,'--'));
END ;