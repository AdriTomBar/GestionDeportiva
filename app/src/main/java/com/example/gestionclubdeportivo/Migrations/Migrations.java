package com.example.gestionclubdeportivo.Migrations;

import androidx.annotation.NonNull;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

public class Migrations {
    public static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            // Agregar la nueva columna "nueva_columna" a la tabla "equipos"
            database.execSQL("ALTER TABLE equipos ADD COLUMN nueva_columna TEXT DEFAULT 'valor_default'");
        }
    };

    // Otra migración, por ejemplo, de la versión 2 a la 3
    public static final Migration MIGRATION_2_3 = new Migration(2, 3) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            // Supón que agregas otra columna
            database.execSQL("ALTER TABLE equipos ADD COLUMN otra_columna INTEGER DEFAULT 0");
        }
    };

    public static final Migration MIGRATION_5_6 = new Migration(5, 6) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            // Ejemplo de agregar una nueva columna a la tabla "jugadores"
            database.execSQL("ALTER TABLE jugadores ADD COLUMN nueva_columna TEXT DEFAULT 'valor_default'");
        }
    };
}

