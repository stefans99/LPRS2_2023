import serial

# Otvori serijski priključak
ser = serial.Serial('COM4', 9600)  # Promijenite COM4 u odgovarajući serijski priključak

# Otvori datoteku za pisanje
file = open("output.txt", "w")

line = ""  # Varijabla za pohranu zadnje linije

while True:
    # Čitaj podatke sa serijskog porta
    new_line = ser.readline().decode().strip()

    # Ispisi primljene podatke na konzolu
    print(new_line)

    if new_line:
        # Ažuriraj zadnju liniju
        line = new_line

        # Zatvori datoteku prije svakog pisanja nove linije
        file.close()

        # Otvori datoteku u write mode (pisanje na početak datoteke)
        file = open("data.txt", "w")

        # Snimi zadnju liniju u datoteku
        file.write(line + "\n")
        file.flush()

# Zatvori datoteku
file.close()

# Zatvori serijski priključak
ser.close()
