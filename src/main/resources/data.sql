INSERT INTO ACCOUNT (USERNAME, EMAIL, PASSWORD)
       VALUES
           ('SCOTT', 'SCOTT@example.com', 'TIGER')
       ON CONFLICT DO NOTHING;

INSERT INTO PROJECT (NAME, TYPE, STARTDATE, VERSIONNO)
       VALUES
           ('ProjectA', 'PRIVATE', '2017-01-01', 0)
       ON CONFLICT DO NOTHING;


INSERT INTO PROJECT (NAME, TYPE, STARTDATE, VERSIONNO)
       VALUES
           ('ProjectB', 'PUBLIC', '2017-07-07', 0)
       ON CONFLICT DO NOTHING;

INSERT INTO PROJECT (NAME, TYPE, STARTDATE, VERSIONNO)
       VALUES
           ('ProjectC', 'PUBLIC', '2017-08-08', 0)
       ON CONFLICT DO NOTHING;

INSERT INTO PROJECT (NAME, TYPE, STARTDATE, VERSIONNO)
       VALUES
           ('ProjectD', 'PUBLIC', '2017-09-09', 0)
       ON CONFLICT DO NOTHING;



