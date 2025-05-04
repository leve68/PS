-- 코드를 입력하세요
SELECT o.animal_id, o.animal_type, o.name
from animal_outs o
inner join animal_ins i
on o.animal_id = i.animal_id
where o.sex_upon_outcome != i.sex_upon_intake
order by o.animal_id;