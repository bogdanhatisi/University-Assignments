using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Lab03.domain
{
    public class Proba : Identifiable<int>
    {
        private int id;
        private String stil;

        private int distanta;

        public Proba(int id, string stil, int distanta)
        {
            this.id = id;
            this.stil = stil;
            this.distanta = distanta;
        }

        public String getStil()
        {
            return this.stil;
        }

        public int getDistanta()
        {
            return this.distanta;
        }

        public void setStil(String stil)
        {
            this.stil = this.stil;
        }

        public void setDistanta(int distanta)
        {
            this.distanta = this.distanta;
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
