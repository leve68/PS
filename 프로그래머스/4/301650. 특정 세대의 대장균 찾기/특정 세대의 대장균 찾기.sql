-- 코드를 작성해주세요
select e3.ID
from ecoli_data e1
inner join ecoli_data e2
on e1.id = e2.parent_id
inner join ecoli_data e3
on e2.id = e3.parent_id
where e1.parent_id is null and e1.id = e2.parent_id and e2.id = e3.parent_id
order by id;