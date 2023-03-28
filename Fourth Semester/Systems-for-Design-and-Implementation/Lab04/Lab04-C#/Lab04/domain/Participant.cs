using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Lab03.domain
{
    public class Participant : Identifiable<int>
    {
        private int id;
        private String nume;

        private int varsta;

        public Participant(int id, string nume, int varsta)
        {
            this.id = id;
            this.nume = nume;
            this.varsta = varsta;
        }

        public String getNume()
        {
            return this.nume;
        }

        public int getVarsta()
        {
            return this.varsta;
        }
        

        public void setNume(String nume)
        {
            this.nume = this.nume;
        }

      

        public void setId(int id)
        {
            this.setId(id);
        }

        int Identifiable<int>.getId()
        {
            throw new NotImplementedException();
        }
    }
   
}
