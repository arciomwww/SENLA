select facid, extract(month from starttime) as month, sum(slots) as slots
    from cd.bookings
    where
        starttime >= '2012-01-01'
        and starttime < '2013-01-01'
    group by facid, month
union all
select facid, null, sum(slots) as slots
    from cd.bookings
    where
        starttime >= '2012-01-01'
        and starttime < '2013-01-01'
    group by facid
union all
select null, null, sum(slots) as slots
    from cd.bookings
    where
        starttime >= '2012-01-01'
        and starttime < '2013-01-01'
order by facid, month;