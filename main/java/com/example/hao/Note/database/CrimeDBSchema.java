package com.example.hao.Note.database;

/**
 * Created by hao on 2016-08-10.
 */
public class CrimeDBSchema {

    public static final class CrimeTable{
        public static final String NAME = "crimes";

        public static final class Cols{
            public static final String UUID = "uuid";
            public static final String TITLE = "title";
            public static final String DATE = "date";
            public static final String SOLVED = "solved";
            public static final String SUSPECT = "suspect";
            public static final String DESCRIPTION = "description";

        }
    }
}
