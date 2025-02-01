// app/src/main/java/com/example/gestionclubdeportivo/Database/AppDatabase.java
package com.example.gestionclubdeportivo.Database;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.gestionclubdeportivo.models.Equipo;
import com.example.gestionclubdeportivo.models.Jugador;
import com.example.gestionclubdeportivo.DAOs.EquipoDao;
import com.example.gestionclubdeportivo.DAOs.DaoJugador;

@Database(entities = {Equipo.class, Jugador.class}, version = 4) // Increase the version number
public abstract class AppDatabase extends RoomDatabase {
    public abstract EquipoDao equipoDao();
    public abstract DaoJugador jugadorDao();

    // Define migration from version 3 to version 4
    public static final Migration MIGRATION_3_4 = new Migration(3, 4) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            // Perform necessary migration steps here
            // For example, adding a new column:
            // database.execSQL("ALTER TABLE Jugador ADD COLUMN new_column_name INTEGER NOT NULL DEFAULT 0");
        }
    };

    // Define migration from version 2 to version 3
    public static final Migration MIGRATION_2_3 = new Migration(2, 3) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            // Perform necessary migration steps here
            // For example, adding a new column:
            // database.execSQL("ALTER TABLE Jugador ADD COLUMN new_column_name INTEGER NOT NULL DEFAULT 0");
        }
    };
}