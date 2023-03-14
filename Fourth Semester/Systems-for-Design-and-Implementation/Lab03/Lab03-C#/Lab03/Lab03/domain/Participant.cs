using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Lab03.domain
{
    public class Participant : Identifiable<int>
    {

        private String nume;

        private int varsta;

        private String probe;

        public Participant(String nume, int varsta, String probe)
        {
            this.nume = this.nume;
            this.varsta = this.varsta;
            this.probe = this.probe;
        }

        public String getNume()
        {
            return this.nume;
        }

        public int getVarsta()
        {
            return this.varsta;
        }

        public String getProbe()
        {
            return this.probe;
        }

        public void setNume(String nume)
        {
            this.nume = this.nume;
        }

      

        public void setId(int id)
        {
            throw new NotImplementedException();
        }

        int Identifiable<int>.getId()
        {
            throw new NotImplementedException();
        }
    }
   
}
